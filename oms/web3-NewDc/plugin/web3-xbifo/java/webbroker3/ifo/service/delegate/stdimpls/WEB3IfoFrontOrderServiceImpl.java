head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoFrontOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����T�[�r�XImpl(WEB3IfoFrontOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/25 ���G�� (���u) �V�K�쐬 ���f��No.587
Revision History : 2007/03/27 ���G�� (���u) �d�l�ύX ���f��No.633
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3ChangeCancelEnableFlag;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�����T�[�r�XImpl)<BR>
 * �敨OP�����T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3IfoFrontOrderServiceImpl implements WEB3IfoFrontOrderService
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoFrontOrderServiceImpl.class);

    /**
     * (get�t�����g�����V�X�e���敪)<BR>
     * �����̎s��R�[�h�ɂ��A�t�����g�����V�X�e���敪���擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̎s��R�[�h=="����"�̏ꍇ�́A<BR>
     * �@@�@@"���؁AJASDAQ�I�[�N�V�����A����"��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����̎s��R�[�h=="���"�̏ꍇ�́A"���"��ԋp����B<BR>
     * <BR>
     * <BR>
     * �R�j�@@�����̎s��R�[�h����L������ł��Ȃ��ꍇ�́A"���̑�"��ԋp����B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * WEB�V�̎s��R�[�h<BR>
     * @@return String
     */
    public String getFrontOrderSystemCode(String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "getFrontOrderSystemCode(String)";
        log.entering(STR_METHOD_NAME);

        //  �P�j�@@�����̎s��R�[�h=="����"�̏ꍇ�́A
        //  �@@�@@"���؁AJASDAQ�I�[�N�V�����A����"��ԋp����B
        String l_strFrontOrderSystemCode = null;
        if (WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.TOKYO_STOCK_JASDAQ_AUCTION;
        }
        //  �Q�j�@@�����̎s��R�[�h=="���"�̏ꍇ�́A"���"��ԋp����B
        else if (WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK;
        }
        //  �R�j�@@�����̎s��R�[�h����L������ł��Ȃ��ꍇ�́A"���̑�"��ԋp����B
        else
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.OTHERS;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strFrontOrderSystemCode;
    }

    /**
     * (get�����o�H�敪)<BR>
     * �����\�Ȕ����o�H�敪���擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get�t�����g�����V�X�e���敪()�ɂ��A<BR>
     * �@@�@@�t�����g�����V�X�e���敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�t�����g�����V�X�e���敪()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̎s��R�[�h<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@������ؑ�.get�L��������ؑ�()�ɂ��A�L���Ȕ�����ؑփI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@��������ؑ�.get�L��������ؑ�()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����̏،���ЃR�[�h<BR>
     * �@@�@@�@@�����^�C�v�F�@@"�敨�I�v�V����"<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̎s��R�[�h<BR>
     * �@@�@@�@@�t�����g�����V�X�e���敪�F�@@�P�j�ŋ��߂��t�����g�����V�X�e���敪<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �R�j�@@�Q�j�Ŗ߂�l!=null�̏ꍇ�́A�߂�l.�����o�H�敪��ԋp����B<BR>
     * �@@�@@�@@�Q�j�Ŗ߂�l==null�̏ꍇ�́A�u�����o�H�֑ؑΏۂȂ��v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02216<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * WEB�V�̎s��R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSubmitOrderRouteDiv(
        String l_strInstitutionCode,
        String l_strMarketCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubmitOrderRouteDiv(String, String)";
        log.entering(STR_METHOD_NAME);

        //    �P�j�@@this.get�t�����g�����V�X�e���敪()�ɂ��A
        //  �@@�@@�t�����g�����V�X�e���敪���擾����B
        //  �@@�@@�@@��get�t�����g�����V�X�e���敪()�F�����ݒ�d�l��
        //  �@@�@@�@@�s��R�[�h�F�@@�����̎s��R�[�h
        String l_strFrontOrderSystemCode = this.getFrontOrderSystemCode(l_strMarketCode);

        //  �Q�j�@@������ؑ�.get�L��������ؑ�()�ɂ��A�L���Ȕ�����ؑփI�u�W�F�N�g���擾����B
        //  �@@�@@�@@��������ؑ�.get�L��������ؑ�()�F�����ݒ�d�l��
        //  �@@�@@�@@�،���ЃR�[�h�F�@@�����̏،���ЃR�[�h
        //  �@@�@@�@@�����^�C�v�F�@@"�敨�I�v�V����"
        //  �@@�@@�@@�s��R�[�h�F�@@�����̎s��R�[�h
        //  �@@�@@�@@�t�����g�����V�X�e���敪�F�@@�P�j�ŋ��߂��t�����g�����V�X�e���敪
        WEB3GentradeOrderSwitching l_orderSwitching =
            WEB3GentradeOrderSwitching.getOnOrderSwitching(
                l_strInstitutionCode,
                ProductTypeEnum.IFO,
                l_strMarketCode,
                l_strFrontOrderSystemCode);

        //  �R�j�@@�Q�j�Ŗ߂�l!=null�̏ꍇ�́A�߂�l.�����o�H�敪��ԋp����B
        //  �@@�@@�@@�Q�j�Ŗ߂�l==null�̏ꍇ�́A�u�����o�H�֑ؑΏۂȂ��v�̗�O��throw����B
        if (l_orderSwitching != null)
        {
            OrderSwitchingRow l_orderSwitchingRow =
                (OrderSwitchingRow)l_orderSwitching.getDataSourceObject();

            log.exiting(STR_METHOD_NAME);
            return l_orderSwitchingRow.getSubmitOrderRouteDiv();
        }
        else
        {
            log.debug("�����o�H�֑ؑΏۂȂ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02216,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h:" + l_strInstitutionCode
                + " �s��R�[�h:" + l_strMarketCode
                + " �t�����g�����V�X�e���敪:" + l_strFrontOrderSystemCode);
        }
    }

    /**
     * (get����Rev�J�n�ʒuIN�Г���������)<BR>
     * �Г��������ڂ̕����񒆂́A����Rev.�J�n�ʒu��Ԃ��B<BR>
     * <BR>
     * �J�n�ʒu�Ƃ��āA16��Ԃ��B<BR>
     * <BR>
     * �Г��������ڂ̃R�[�h�̌n�F<BR>
     * �،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{����Rev.�{"999"<BR>
     * <BR>
     * @@return int
     */
    public int getIndexOfOrderRevInCorpCode()
    {
        final String STR_METHOD_NAME = "getIndexOfOrderRevInCorpCode()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return 16;
    }

    /**
     * (get����Rev����)<BR>
     * ����Rev.�̌�����Ԃ��B<BR>
     * <BR>
     * �����Ƃ��āA2��Ԃ��B<BR>
     * <BR>
     * @@return int
     */
    public int getFigureOfOrderRev()
    {
        final String STR_METHOD_NAME = "getFigureOfOrderRev()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return 2;
    }

    /**
     * (get�Г���������)<BR>
     * �����̒����P�ʃI�u�W�F�N�g���A�����Ɏg�p����u�Г��������ځv�ݒ�p�������<BR>
     * �擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@���X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�����̒����P��.getBranch( )�ɂĎ擾����B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̕������ԋp����B<BR>
     * <BR>
     * �@@�@@�،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{����Rev.�{"999"<BR>
     * <BR>
     * �@@�@@���X�I�u�W�F�N�g����擾�F�@@�،����ID�A���X�R�[�h<BR>
     * �@@�@@�����̒����P�ʃI�u�W�F�N�g����擾�F�@@�����^�C�v�A���ʃR�[�h�A����Rev.<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCorpCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCorpCode(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@���X�I�u�W�F�N�g���擾����B
        // �@@�@@�@@�����̒����P��.getBranch()�ɂĎ擾����B
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        BranchRow l_branchRow = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        // �Q�j�@@�ȉ��̕������ԋp����B
        // �@@�@@�،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{����Rev.�{"999"
        // �@@�@@���X�I�u�W�F�N�g����擾�F�@@�،����ID�A���X�R�[�h
        // �@@�@@�����̒����P�ʃI�u�W�F�N�g����擾�F�@@�����^�C�v�A���ʃR�[�h�A����Rev.
        StringBuffer l_strCorpCode = new StringBuffer();
        l_strCorpCode.append(Long.toString(l_branchRow.getInstitutionId()));
        l_strCorpCode.append(l_branchRow.getBranchCode());
        l_strCorpCode.append(Integer.toString(l_orderUnitRow.getProductType().intValue()));
        l_strCorpCode.append(l_orderUnitRow.getOrderRequestNumber());
        l_strCorpCode.append(l_orderUnitRow.getOrderRev());
        l_strCorpCode.append("999");

        log.exiting(STR_METHOD_NAME);
        return l_strCorpCode.toString();
    }

    /**
     * (is�s��ʒm������IN�x�e���ԑ�)<BR>
     * ��������x�e���Ԓ��̎��ԑтɂ����āA<BR>
     * �w��̒����Ɋ֌W����f�[�^�i�����A������܂ށj���s��ɒʒm���ł��邩�ǂ�����<BR>
     * �敨OP��������L���[�e�[�u���̃f�[�^��蔻�肵�Ԃ��B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����̂����ꂩ�ɍ��v����ꍇ��false��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�i��������^��c�Ɠ��j<BR>
     * �@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ�i���ꒆ�Ŏ�����͎�����j<BR>
     * <BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�������s�ꂩ��̖߂��҂��Ă����Ԃ̏ꍇ(*1)�̂݁A<BR>
     * �@@�@@�@@�敨OP��������L���[�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h=�����̒����P��.getBranch().�،���ЃR�[�h<BR>
     * �@@�@@���@@���X�R�[�h=�����̒����P��.getBranch().���X�R�[�h<BR>
     * �@@�@@���@@���ʃR�[�h=�����̒����P��.���ʃR�[�h<BR>
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*2)=�����̒����P��.����Rev.<BR>
     * �@@�@@���@@�����敪!="0�F������"<BR>
     * <BR>
     * �@@�@@(*1)�������s�ꂩ��̖߂��҂��Ă����Ԃ̏ꍇ<BR>
     * �@@�@@�@@�@@�����̒����P��.������Ԃ��ȉ��̂����ꂩ�ɍ��v����ꍇ�A<BR>
     * �@@�@@�@@�@@�������s�ꂩ��̖߂��҂��Ă����Ԃł���Ɣ��肷��B<BR>
     * �@@�@@�@@�@@---------------------------------<BR>
     * �@@�@@�@@�@@�@@�@@ACCEPTED<BR>
     * �@@�@@�@@�@@�@@�@@MODIFY_ACCEPTED<BR>
     * �@@�@@�@@�@@�@@�@@ORDERING<BR>
     * �@@�@@�@@�@@---------------------------------<BR>
     * <BR>
     * �@@�@@(*2)�J�n�ʒu�A������<BR>
     * �@@�@@�@@�@@this.get����Rev�J�n�ʒuIN�Г���������()�Aget����Rev����()��<BR>
     * �@@�@@�@@�@@�擾���w�肷��B<BR>
     * <BR>
     * �R�j�@@�Y������f�[�^�����݂��Ȃ��ꍇ�́Afalse��ԋp����B<BR>
     * �@@�@@�@@�Y���f�[�^�����݂���ꍇ�́Atrue��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isMarketNotifyingOrderInBreakTimeZone(
        IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMarketNotifyingOrderInBreakTimeZone(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //    �P�j�@@�ȉ��̏����̂����ꂩ�ɍ��v����ꍇ��false��ԋp����B
        // �@@�@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�i��������^��c�Ɠ��j
        // �@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ�i���ꒆ�Ŏ�����͎�����j
        // �@@�@@�@@�ȊO�A�ȉ��̏������s���B
        if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()
            || !WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if (!OrderStatusEnum.ACCEPTED.equals(l_orderStatus)
            && !OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            && !OrderStatusEnum.ORDERING.equals(l_orderStatus))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        // �Q�j�@@�������s�ꂩ��̖߂��҂��Ă����Ԃ̏ꍇ(*1)�̂݁A
        // �@@�@@�@@�敨OP��������L���[�e�[�u�����ȉ��̏����Ō�������B
        // �@@�@@[����]
        // �@@�@@�@@�،���ЃR�[�h=�����̒����P��.getBranch().�،���ЃR�[�h
        // �@@�@@���@@���X�R�[�h=�����̒����P��.getBranch().���X�R�[�h
        // �@@�@@���@@���ʃR�[�h=�����̒����P��.���ʃR�[�h
        // �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*2)=�����̒����P��.����Rev.
        // �@@�@@���@@�����敪!="0�F������"
        // �@@�@@(*1)�������s�ꂩ��̖߂��҂��Ă����Ԃ̏ꍇ
        // �@@�@@�@@�@@�����̒����P��.������Ԃ��ȉ��̂����ꂩ�ɍ��v����ꍇ�A
        // �@@�@@�@@�@@�������s�ꂩ��̖߂��҂��Ă����Ԃł���Ɣ��肷��B
        // �@@�@@�@@�@@---------------------------------
        // �@@�@@�@@�@@�@@�@@ACCEPTED
        // �@@�@@�@@�@@�@@�@@MODIFY_ACCEPTED
        // �@@�@@�@@�@@�@@�@@ORDERING
        // �@@�@@�@@�@@---------------------------------
        // �@@�@@(*2)�J�n�ʒu�A������
        // �@@�@@�@@�@@this.get����Rev�J�n�ʒuIN�Г���������()�Aget����Rev����()��
        // �@@�@@�@@�@@�擾���w�肷��B
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        Object[] l_objWhere =
        {
            l_branchRow.getInstitutionCode(),
            l_branchRow.getBranchCode(),
            l_orderUnitRow.getOrderRequestNumber(),
            l_orderUnitRow.getOrderRev(),
            WEB3FrontOrderStatusDef.NOT_DEAL
        };
        int l_intIndex = this.getIndexOfOrderRevInCorpCode();
        int l_intFigure = this.getFigureOfOrderRev();
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append("institution_code = ?");
        l_strWhere.append(" and branch_code = ?");
        l_strWhere.append(" and order_request_number = ?");
        l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ") = ?");
        l_strWhere.append(" and status != ?");
        List l_lisSearchResult = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisSearchResult = l_processor.doFindAllQuery(
                HostFotypeOrderAllRow.TYPE,
                l_strWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataException l_de)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        // �R�j�@@�Y������f�[�^�����݂��Ȃ��ꍇ�́Afalse��ԋp����B
        // �@@�@@�@@�Y���f�[�^�����݂���ꍇ�́Atrue��ԋp����B
        log.exiting(STR_METHOD_NAME);

        int l_intListSize = 0;
        if (l_lisSearchResult != null)
        {
            l_intListSize = l_lisSearchResult.size();
        }
        if (l_intListSize == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * (get�敨OP��������L���[)<BR>
     * �w��̒����P�ʂɊY������敨OP��������L���[���擾���Ԃ��B<BR>
     * <BR>
     * �P�j�@@�敨OP��������L���[�e�[�u�����A�ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = �����̒����P��.getBranch().�،���ЃR�[�h<BR>
     * �@@�@@���@@���X�R�[�h = �����̒����P��.getBranch().���X�R�[�h<BR>
     * �@@�@@���@@���ʃR�[�h = �����̒����P��.���ʃR�[�h<BR>
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*1) =  �����̒����P��.����Rev.<BR>
     * �@@�@@���@@�����敪 = "������"<BR>
     * <BR>
     * �@@�@@(*1)�J�n�ʒu�A������<BR>
     * �@@�@@�@@�@@this.get����Rev�J�n�ʒuIN�Г���������()�Aget����Rev����()��<BR>
     * �@@�@@�@@�@@�擾���w�肷��B<BR>
     * <BR>
     * �Q�j�@@�擾�����敨OP��������L���[Params��ԋp����B<BR>
     * �@@�@@�@@�Y������f�[�^�����݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return HostFotypeOrderAllParams
     * @@throws WEB3BaseException
     */
    public HostFotypeOrderAllParams getHostFotypeOrderAll(
        IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHostFotypeOrderAll(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        Object[] l_objWhere =
        {
            l_branchRow.getInstitutionCode(),
            l_branchRow.getBranchCode(),
            l_orderUnitRow.getOrderRequestNumber(),
            l_orderUnitRow.getOrderRev(),
            WEB3FrontOrderStatusDef.NOT_DEAL
        };
        int l_intIndex = this.getIndexOfOrderRevInCorpCode();
        int l_intFigure = this.getFigureOfOrderRev();
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append("institution_code = ?");
        l_strWhere.append(" and branch_code = ?");
        l_strWhere.append(" and order_request_number = ?");
        l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ") = ?");
        l_strWhere.append(" and status = ?");
        List l_lisSearchResult = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisSearchResult = l_processor.doFindAllQuery(
                HostFotypeOrderAllRow.TYPE,
                l_strWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        HostFotypeOrderAllParams l_params = null;
        int l_intListSize = 0;
        if (l_lisSearchResult != null)
        {
            l_intListSize = l_lisSearchResult.size();
        }
        if (l_intListSize > 0)
        {
            l_params = (HostFotypeOrderAllParams)l_lisSearchResult.get(0);
        }
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * (get����������Rev)<BR>
     * �����̒����㒍���P�ʃI�u�W�F�N�g���A<BR>
     * �������ɒ����P�ʃe�[�u��.����Rev�ɐݒ肷�镶������擾���Ԃ��B<BR>
     * ------------------- <BR>
     * �������P�ʃe�[�u��.����Rev.�ݒ�l�ɂ���<BR>
     * �@@�|���s�����������܂ށA�ڋq�̒����I�y���[�V�����̃J�E���^�B�s��֑�������̉񐔂��ݒ肳���B<BR>
     * �@@�|�����܂Ŏs��֑�������̉񐔂݂̂��Ǘ�����K�v�����邽�߁A<BR>
     * �@@�@@�@@�ȉ��̃P�[�X�ɊY����������̏ꍇ�̓J�E���g�A�b�v���Ă͂����Ȃ��B<BR>
     * �@@�@@�@@�i�P�j�s�ꖢ���M�����ɑ΂��������<BR>
     * �@@�@@�@@�i�Q�j�������e���s��ʒm�s�v�ȏꍇ<BR>
     * ------------------- <BR>
     * <BR>
     * �P�j�@@�s��ʒm�s�v�̒���(*1)�̏ꍇ�́A�����̒����P��.����Rev.�����̂܂ܕԋp����B<BR>
     * <BR>
     * �@@�@@(*1)�s��ʒm�s�v�̒���<BR>
     * �@@�@@�@@�@@OP�����}�l�[�W��.is���e�ʒm�ϒ���(�����̒����P��)==true�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�s��ʒm�s�v�̒����Ɣ��肷��B<BR>
     * <BR>
     * �Q�j�@@�s��ǎ��ԑ�(*2) �܂��� �s�ꖢ���M�����i*3)�̏ꍇ�́A<BR>
     * �@@�@@�@@�����̒����P��.����Rev.�����̂܂ܕԋp����B<BR>
     * <BR>
     * �@@�@@(*2)�s��ǎ��ԑ�<BR>
     * �@@�@@�@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�s��ǎ��ԑтƔ��肷��B<BR>
     * <BR>
     * �@@�@@(*3)�s�ꖢ���M����<BR>
     * �@@�@@�@@�@@�����̒����P��.�s�ꂩ��m�F�ς̐��� == NaN�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�s�ꖢ���M�����Ɣ��肷��B<BR>
     * <BR>
     * �R�j�@@��L�ȊO�̏ꍇ�́A�ȉ��̒ʂ�Ƃ���B<BR>
     * �@@�@@�@@�E�߂�l�̕�����́Athis.get����Rev����()�̌����Œ�i�擪��0���߁j�Ƃ���B<BR>
     * �@@�@@�@@�E�����𒴂���l�ƂȂ����ꍇ�́A�u����Rev.�̒l���ő包���𒴉߁v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02185<BR>
     * <BR>
     * �R�|�P�j�@@�������������̎��ԑ�(*4)�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�����̒����P��.����Rev.�Ɂ{�P�����������ԋp����B<BR>
     * <BR>
     * �@@�@@(*4)������������<BR>
     * �@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�������������̎��ԑтƔ��肷��B<BR>
     * <BR>
     * �R�|�Q�j�@@��L�ȊO�̏ꍇ�́A�敨OP��������L���[�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = �����̒����㒍���P��.getBranch().�،���ЃR�[�h<BR>
     * �@@�@@���@@���X�R�[�h = �����̒����㒍���P��.getBranch().���X�R�[�h<BR>
     * �@@�@@���@@���ʃR�[�h = �����̒����㒍���P��.���ʃR�[�h <BR>
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*5) =  �����̒����㒍���P��.����Rev.<BR>
     * �@@�@@���@@�����敪 = "������"<BR>
     * <BR>
     * �@@�@@(*5)�J�n�ʒu�A������<BR>
     * �@@�@@�@@�@@�敨OP�����T�[�r�X.get����Rev�J�n�ʒuIN�Г���������()�Aget����Rev����()��<BR>
     * �@@�@@�@@�@@�擾���w�肷��B<BR>
     * <BR>
     * �R�|�R�j�@@�������̒���Rev.�̒l�����߂�B<BR>
     * <BR>
     * �@@�@@���R�|�Q�j�ŊY�����郌�R�[�h�����݂��Ȃ��ꍇ<BR>
     * �@@�@@�@@�@@�����̒����P��.����Rev.�Ɂ{�P�����l��ԋp����B<BR>
     * <BR>
     * �@@�@@���R�|�Q�j�ŊY�����郌�R�[�h�����݂���ꍇ<BR>
     * �@@�@@�@@�����̒����P��.����Rev.�����̂܂ܕԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����㒍���P��)<BR>
     * ������̒����P�ʃI�u�W�F�N�g�B<BR>
     * �ixTrade�W�����ڂɁA������̒l���ݒ肳��Ă���I�u�W�F�N�g�j<BR>
     * ���X�V�C���^�Z�v�^.mutate()��������R�[������邱�Ƃ�O��Ƃ��Ă���B<BR>
     * <BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeOrderRev(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderRev(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //    �P�j�@@�s��ʒm�s�v�̒���(*1)�̏ꍇ�́A�����̒����P��.����Rev.�����̂܂ܕԋp����B
        //  �@@�@@(*1)�s��ʒm�s�v�̒���
        //  �@@�@@�@@�@@OP�����}�l�[�W��.is���e�ʒm�ϒ���(�����̒����P��)==true�̏ꍇ�́A
        //  �@@�@@�@@�@@�s��ʒm�s�v�̒����Ɣ��肷��B
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderRev = l_orderUnitRow.getOrderRev();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        if (l_orderManagerImpl.isNotifyEndOrder(l_orderUnit))
        {
            log.debug(STR_METHOD_NAME);
            return l_strOrderRev;
        }

        //  �Q�j�@@�s��ǎ��ԑ�(*2)�܂��͎s�ꖢ���M�����i*3)�̏ꍇ�́A
        //  �@@�@@�@@�����̒����P��.����Rev.�����̂܂ܕԋp����B
        //  �@@�@@(*2)�s��ǎ��ԑ�
        //  �@@�@@�@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�́A
        //  �@@�@@�@@�@@�s��ǎ��ԑтƔ��肷��B
        //
        //  �@@�@@(*3)�s�ꖢ���M����
        //  �@@�@@�@@�@@�����̒����P��.�s�ꂩ��m�F�ς̐���==NaN�̏ꍇ�́A
        //  �@@�@@�@@�@@�s�ꖢ���M�����Ɣ��肷��B
        if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()
            || Double.isNaN(l_orderUnit.getConfirmedQuantity()))
        {
            log.debug(STR_METHOD_NAME);
            return l_strOrderRev;
        }

        //
        //  �R�j�@@��L�ȊO�̏ꍇ�́A�ȉ��̒ʂ�Ƃ���B
        //  �@@�@@�@@�E�߂�l�̕�����́Athis.get����Rev����()�̌����Œ�i�擪��0���߁j�Ƃ���B
        //  �@@�@@�@@�E�����𒴂���l�ƂȂ����ꍇ�́A�u����Rev.�̒l���ő包���𒴉߁v�̗�O��throw����B
        //  �R�|�P�j�@@�������������̎��ԑ�(*4)�̏ꍇ�́A
        //  �@@�@@�@@�@@�@@�����̒����P��.����Rev.�Ɂ{�P�����������ԋp����B
        //  �@@�@@(*4)������������
        //  �@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ�́A
        //  �@@�@@�@@�@@�������������̎��ԑтƔ��肷��B
        if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
        {
            int l_intOrderRev = Integer.parseInt(l_strOrderRev);
            l_intOrderRev += 1;
            l_strOrderRev = Integer.toString(l_intOrderRev);
            int l_intFigureOfOrderRev = this.getFigureOfOrderRev();
            int l_intLength = l_strOrderRev.length();
            if (l_intLength > l_intFigureOfOrderRev)
            {
                log.debug("����Rev.�̒l���ő包���𒴉߁B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            int l_intSize = l_intFigureOfOrderRev - l_intLength;
            StringBuffer l_sbOrderRev = new StringBuffer();
            for (int i = 0; i < l_intSize; i++)
            {
                l_sbOrderRev.append("0");
            }
            l_sbOrderRev.append(l_strOrderRev);

            log.exiting(STR_METHOD_NAME);
            return l_sbOrderRev.toString();
        }

        //  �R�|�Q�j�@@��L�ȊO�̏ꍇ�́A�敨OP��������L���[�e�[�u�����ȉ��̏����Ō�������B
        //  �@@�@@[����]
        //  �@@�@@�@@�،���ЃR�[�h=�����̒����㒍���P��.getBranch().�،���ЃR�[�h
        //  �@@�@@���@@���X�R�[�h=�����̒����㒍���P��.getBranch().���X�R�[�h
        //  �@@�@@���@@���ʃR�[�h=�����̒����㒍���P��.���ʃR�[�h
        //  �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*5)=�����̒����㒍���P��.����Rev.
        //  �@@�@@���@@�����敪="������"
        //  �@@�@@(*5)�J�n�ʒu�A������
        //  �@@�@@�@@�@@�敨OP�����T�[�r�X.get����Rev�J�n�ʒuIN�Г���������()�Aget����Rev����()��
        //  �@@�@@�@@�@@�擾���w�肷��B
        else
        {
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Branch l_branch = null;
            try
            {
                l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            Object[] l_objWhere =
            {
                l_branchRow.getInstitutionCode(),
                l_branchRow.getBranchCode(),
                l_orderUnitRow.getOrderRequestNumber(),
                l_orderUnitRow.getOrderRev(),
                WEB3FrontOrderStatusDef.NOT_DEAL
            };

            int l_intIndex = this.getIndexOfOrderRevInCorpCode();
            int l_intFigure = this.getFigureOfOrderRev();
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append("institution_code = ?");
            l_strWhere.append(" and branch_code = ?");
            l_strWhere.append(" and order_request_number = ?");
            l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ") = ?");
            l_strWhere.append(" and status = ?");
            List l_lisSearchResult = null;

            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_lisSearchResult = l_processor.doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_strWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataException l_de)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
            }

            int l_intListSize = 0;
            if (l_lisSearchResult != null)
            {
                l_intListSize = l_lisSearchResult.size();
            }

            if (l_intListSize == 0)
            {
                //  �R�|�R�j�@@�������̒���Rev.�̒l�����߂�B
                //  �@@�@@���R�|�Q�j�ŊY�����郌�R�[�h�����݂��Ȃ��ꍇ
                //  �@@�@@�@@�@@�����̒����P��.����Rev.�Ɂ{�P�����l��ԋp����B
                int l_lngOrderRev = Integer.parseInt(l_strOrderRev);
                l_lngOrderRev += 1;
                l_strOrderRev = Integer.toString(l_lngOrderRev);
                int l_intFigureOfOrderRev = this.getFigureOfOrderRev();
                int l_intLength = l_strOrderRev.length();
                if (l_intLength > l_intFigureOfOrderRev)
                {
                    log.debug("����Rev.�̒l���ő包���𒴉߁B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                int l_intSize = l_intFigureOfOrderRev - l_intLength;
                StringBuffer l_sbOrderRev = new StringBuffer();
                for (int i = 0; i < l_intSize; i++)
                {
                    l_sbOrderRev.append("0");
                }
                l_sbOrderRev.append(l_strOrderRev);

                log.exiting(STR_METHOD_NAME);
                return l_sbOrderRev.toString();
            }
            //  �@@�@@���R�|�Q�j�ŊY�����郌�R�[�h�����݂���ꍇ
            //  �@@�@@�@@�����̒����P��.����Rev.�����̂܂ܕԋp����B
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_strOrderRev;
            }
        }
    }

    /**
     * (lock�敨OP��������L���[)<BR>
     * �w��̒����P�ʂ̐敨OP��������L���[�f�[�^�ɋ��L���b�N��������B<BR>
     * <BR>
     * �P�j�@@�����̒����P�ʃI�u�W�F�N�g���w�肵�A<BR>
     * �@@�@@�@@�敨OP��������L���[TransactionCallback�N���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@��������TransactionCallback�N���X���w�肵�A<BR>
     * �@@�@@�@@QueryProcessor.doTransaction()�����s����B<BR>
     * �@@�@@�@@�i�g�����U�N�V���������F TX_JOIN_EXISTING�j<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void lockHostFotypeOrderAll(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "lockHostFotypeOrderAll(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����̒����P�ʃI�u�W�F�N�g���w�肵�A
        //�@@�@@�@@�敨OP��������L���[TransactionCallback�N���X�𐶐�����B
        WEB3IfoOrderAllTransactionCallback l_transactionCallback =
            new WEB3IfoOrderAllTransactionCallback(l_orderUnit);

        //�Q�j�@@��������TransactionCallback�N���X���w�肵�A
        //�@@�@@�@@QueryProcessor.doTransaction()�����s����B
        //�@@�@@�@@�i�g�����U�N�V���������FTX_JOIN_EXISTING�j
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_transactionCallback);
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (get������MQ�f�[�^�R�[�h)<BR>
     * �����o�H�敪�A�敨�I�v�V�����敪�ɂ��A<BR>
     * �������Ɏg�p����MQ�f�[�^�R�[�h�ݒ�p��������擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.�����o�H�敪=="9�F������~"�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����̒����P��.�����o�H�敪==�@@"SONAR���n"�̏ꍇ�́A<BR>
     * <BR>
     * �@@�Q�|�P�j�����̒����P��.�敨/�I�v�V�����敪 == �h�敨�h�̏ꍇ�A�hEI803T�h�i�敨�j<BR>
     * <BR>
     * �@@�Q�|�Q�j�Q�|�P�j�ȊO�A�hEI801T�h�i�I�v�V�����j<BR>
     * <BR>
     * �@@��ԋp����B<BR>
     * <BR>
     * �R�j�@@�����̒����P��.�����o�H�敪==�@@"�t�����g���n"�̏ꍇ�́A"EI8X2T"��ԋp����B<BR>
     * <BR>
     * <BR>
     * �S�j�@@�����̒����P��.�����o�H�敪����L�ȊO�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrderMQDataCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderMQDataCode(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        //�P�j�@@�����̒����P��.�����o�H�敪=="9�F������~"�̏ꍇ�́Anull��ԋp����B
        if (WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("�����̒����P��.�����o�H�敪==9�F������~�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�����̒����P��.�����o�H�敪==�@@"SONAR���n"�̏ꍇ�́A
        //�@@�Q�|�P�j�����̒����P��.�敨/�I�v�V�����敪==�h�敨�h�̏ꍇ�A�hEI803T�h�i�敨�j
        //�@@�Q�|�Q�j�Q�|�P�j�ȊO�A�hEI801T�h�i�I�v�V�����j
        //�@@��ԋp����B
        else if (WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION.equals(
            l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("�����̒����P��.�����o�H�敪==�@@SONAR���n�̏ꍇ");
            if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
            {
                log.debug("�����̒����P��.�敨/�I�v�V�����敪==�h�敨�h�̏ꍇ");
                log.exiting(STR_METHOD_NAME);
                return "EI803T";
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return "EI801T";
            }
        }

        //�R�j�@@�����̒����P��.�����o�H�敪==�@@"�t�����g���n"�̏ꍇ�́A"EI8X2T"��ԋp����B
        else if (WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION.equals(
            l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("�����̒����P��.�����o�H�敪==�@@�t�����g���n�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return "EI8X2T";
        }

        //�S�j�@@�����̒����P��.�����o�H�敪����L�ȊO�̏ꍇ�́Anull��ԋp����
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get���������MQ�f�[�^�R�[�h)<BR>
     * �����o�H�敪�A�敨�I�v�V�����敪�ɂ��A<BR>
     * ����������Ɏg�p����MQ�f�[�^�R�[�h�ݒ�p��������擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.�����o�H�敪=="9�F������~"�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����̒����P��.�����o�H�敪==�@@"SONAR���n"�̏ꍇ�́A<BR>
     * �@@�Q�|�P�j�@@�����̒����P��.�敨/�I�v�V�����敪 == �h�敨�h�̏ꍇ�A�hEI804T�h�i�敨�j<BR>
     * �@@�Q�|�Q�j�@@�Q�|�P�j�ȊO�A�hEI802T�h�i�I�v�V�����j<BR>
     * �@@��ԋp����B<BR>
     * <BR>
     * �R�j�@@�����̒����P��.�����o�H�敪==�@@"�t�����g���n"�̏ꍇ�́A"EI8X2T"��ԋp����B<BR>
     * <BR>
     * �S�j�@@�����̒����P��.�����o�H�敪����L�ȊO�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeCancelMQDataCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeCancelMQDataCode(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@�����̒����P��.�����o�H�敪=="9�F������~"�̏ꍇ�́Anull��ԋp����B
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("�����̒����P��.�����o�H�敪==9�F������~�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�����̒����P��.�����o�H�敪==�@@"SONAR���n"�̏ꍇ�́A
        //�@@�Q�|�P�j�@@�����̒����P��.�敨/�I�v�V�����敪==�h�敨�h�̏ꍇ�A�hEI804T�h�i�敨�j
        //�@@�Q�|�Q�j�@@�Q�|�P�j�ȊO�A�hEI802T�h�i�I�v�V�����j
        //�@@��ԋp����B
        else if (WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION.equals(
            l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("�����̒����P��.�����o�H�敪==�@@SONAR���n�̏ꍇ");
            if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
            {
                log.debug("�����̒����P��.�敨/�I�v�V�����敪==�h�敨�h�̏ꍇ");
                log.exiting(STR_METHOD_NAME);
                return "EI804T";
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return "EI802T";
            }
        }

        //�R�j�@@�����̒����P��.�����o�H�敪==�@@"�t�����g���n"�̏ꍇ�́A"EI8X2T"��ԋp����B
        else if (WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION.equals(
            l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("�����̒����P��.�����o�H�敪==�@@�t�����g���n�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return "EI8X2T";
        }
        //�S�j�@@�����̒����P��.�����o�H�敪����L�ȊO�̏ꍇ�́Anull��ԋp����
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get��������������o�H�敪)<BR>
     * �����̒�������Ώۂ̒����P�ʃI�u�W�F�N�g���A�����\�Ȕ����o�H�敪���擾���ԋp����B<BR>
     * �|�����Ƃ��āA���ݗL���Ȕ����o�H��ԋp����B(BR)
     * �|�����o�H�ύX�s�̌o�H��ʂ��Ĕ������������̏ꍇ�́A�������̌o�H��ԋp����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����̒����P��.�����o�H�敪 != "������~"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�ȉ��̏������s���B<BR>
     * �@@�@@�@@�������̒����P��.�����o�H�敪=="������~"�̏ꍇ�́A<BR>
     * �@@�@@�@@�������o�H�ύX�ۂ̃`�F�b�N�͍s��Ȃ��B<BR>
     * <BR>
     * �P�|�P�j�@@this.get�t�����g�����V�X�e���敪()�ɂ��A<BR>
     * �@@�@@�t�����g�����V�X�e���敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�t�����g�����V�X�e���敪()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̒����P��.getMarket().�s��R�[�h<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �P�|�Q�j�@@����������Ɍo�H�̕ύX���\�Ȕ����o�H���ǂ������`�F�b�N����B<BR>
     * �@@�@@�@@�ύX���s�Ȕ����o�H�̏ꍇ�́A�������̔����o�H��ԋp����B<BR>
     * <BR>
     * �P�|�Q�|�P�j�@@�������̔����o�H�敪���g�p��������ؑփN���X�̃C���X�^���X���擾����B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@��������ؑ�()�F�R���X�g���N�^�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����̒����P��.getBranch().�،���ЃR�[�h <BR>
     * �@@�@@�@@�����^�C�v�F�@@"�敨�I�v�V����"  <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̒����P��.getMarket().�s��R�[�h <BR>
     * �@@�@@�@@�����o�H�敪�F�@@�����̒����P��.�����o�H�敪 <BR>
     * �@@�@@�@@�t�����g�����V�X�e���敪�F�@@�P�|�P�j�ŋ��߂��t�����g�����V�X�e���敪 <BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * <BR>
     * �P�|�Q�|�Q�j�@@�ȉ��̏����̂����ꂩ�ɊY������ꍇ�́A <BR>
     * �@@�@@�@@�@@�@@�����̒����P��.�����o�H�敪��ԋp����B <BR>
     * <BR>
     * �@@�@@�E�擾�����C���X�^���X.��������\�t���O == "�s��"�̏ꍇ <BR>
     * �@@�@@�E�擾�����C���X�^���X.isSONAR() == true�̏ꍇ <BR>
     * �@@�@@�E�Y�����锭����ؑփf�[�^�����݂��Ȃ��ꍇ <BR>
     * �@@�@@�@@�@@�i�����^����Ώۂ̒����P�ʂ��A�t�����g�����Ή��s��ɑ΂��钍���ŁA <BR>
     * �@@�@@�@@�@@�@@���@@SONAR���͒����̏ꍇ�́A�Y�����锭����ؑփf�[�^�����݂��Ȃ��B <BR>
     * �@@�@@�@@�@@�@@�iSONAR���͒����̏ꍇ�A�����o�H�敪�ɂ�SONAR�̌o�H���ݒ肳��Ă��邽�߁j�j <BR>
     * <BR>
     * �Q�j�@@������ؑ�.get�L��������ؑ�()�ɂ��A�L���Ȕ�����ؑփI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * �@@�@@�@@��������ؑ�.get�L��������ؑ�()�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����̒����P��.getBranch().�،���ЃR�[�h <BR>
     * �@@�@@�@@�����^�C�v�F�@@"�敨�I�v�V����"  <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̒����P��.getMarket().�s��R�[�h <BR>
     * �@@�@@�@@�t�����g�����V�X�e���敪�F�@@�P�|�P�j�ŋ��߂��t�����g�����V�X�e���敪 <BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * <BR>
     * �R�j�@@�Q�j�Ŗ߂�l != null�̏ꍇ�́A�߂�l.�����o�H�敪 ��ԋp����B <BR>
     * �@@�@@�@@�Q�j�Ŗ߂�l == null�̏ꍇ�́A�u�����o�H�֑ؑΏۂȂ��v�̗�O��throw����B <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02216<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeSubmitOrderRouteDiv(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeSubmitOrderRouteDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_strSubmitOrderRouteDiv = l_orderUnitRow.getSubmitOrderRouteDiv();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        String l_strMarketCode = null;
        try
        {
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            l_strMarketCode = l_market.getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        String l_strFrontOrderSystemCode =
            this.getFrontOrderSystemCode(l_strMarketCode);

        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        String l_strInstitutionCode = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            l_strInstitutionCode = l_branchRow.getInstitutionCode();
        }
        catch (NotFoundException l_nfe)
        {
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        WEB3GentradeOrderSwitching l_orderSwitching = null;
        OrderSwitchingRow l_orderSwitchingRow = null;
        if (!WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_strSubmitOrderRouteDiv))
        {
            try
            {
                l_orderSwitching =
                    new WEB3GentradeOrderSwitching(
                        l_strInstitutionCode,
                        ProductTypeEnum.IFO,
                        l_strMarketCode,
                        l_strSubmitOrderRouteDiv,
                        l_strFrontOrderSystemCode);
            }
            catch (WEB3SystemLayerException l_sle)
            {
                if (!l_sle.getErrorInfo().equals(WEB3ErrorCatalog.SYSTEM_ERROR_80005))
                {
                    throw l_sle;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_strSubmitOrderRouteDiv;
                }
            }

            l_orderSwitchingRow =
                (OrderSwitchingRow)l_orderSwitching.getDataSourceObject();
            if (WEB3ChangeCancelEnableFlag.DISABLE.equals(l_orderSwitchingRow.getChangeCancelEnableFlag()))
            {
                log.exiting(STR_METHOD_NAME);
                return l_strSubmitOrderRouteDiv;
            }
            if (l_orderSwitching.isSONAR())
            {
                log.exiting(STR_METHOD_NAME);
                return l_strSubmitOrderRouteDiv;
            }
        }

        l_orderSwitching =
            WEB3GentradeOrderSwitching.getOnOrderSwitching(
                l_strInstitutionCode,
                ProductTypeEnum.IFO,
                l_strMarketCode,
                l_strFrontOrderSystemCode);

        log.exiting(STR_METHOD_NAME);
        if (l_orderSwitching != null)
        {
            l_orderSwitchingRow =
                (OrderSwitchingRow)l_orderSwitching.getDataSourceObject();
            return l_orderSwitchingRow.getSubmitOrderRouteDiv();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02216,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h:" + l_strInstitutionCode
                + " �s��R�[�h:" + l_strMarketCode
                + " �t�����g�����V�X�e���敪:" + l_strFrontOrderSystemCode);
        }
    }

    /**
     * (get�i������j�Г���������)<BR>
     * �����̒����P�ʃI�u�W�F�N�g���A<BR>
     * �����Ɏg�p����u�i������j�Г��������ځv�ݒ�p��������擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@���X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�����̒����P��.getBranch( )�ɂĎ擾����B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̕������ԋp����B<BR>
     * <BR>
     * �@@�@@�،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{�s�ꂩ��m�F�ς̒���Rev.�{"999"<BR>
     * <BR>
     * �@@�@@���X�I�u�W�F�N�g����擾�F�@@�،����ID�A���X�R�[�h<BR>
     * �@@�@@�����̒����P�ʃI�u�W�F�N�g����擾�F�@@�����^�C�v�A���ʃR�[�h�A�s�ꂩ��m�F�ς̒���Rev.<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrgCorpCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgCorpCode(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //    �P�j�@@���X�I�u�W�F�N�g���擾����B
        // �@@�@@�@@�����̒����P��.getBranch()�ɂĎ擾����B
        // �Q�j�@@�ȉ��̕������ԋp����B
        // �@@�@@�،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{�s�ꂩ��m�F�ς̒���Rev.�{"999"
        // �@@�@@���X�I�u�W�F�N�g����擾�F�@@�،����ID�A���X�R�[�h
        // �@@�@@�����̒����P�ʃI�u�W�F�N�g����擾�F�@@�����^�C�v�A���ʃR�[�h�A�s�ꂩ��m�F�ς̒���Rev.
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        BranchRow l_branchRow = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        StringBuffer l_strCorpCode = new StringBuffer();
        l_strCorpCode.append(Long.toString(l_branchRow.getInstitutionId()));
        l_strCorpCode.append(l_branchRow.getBranchCode());
        l_strCorpCode.append(Integer.toString(l_orderUnitRow.getProductType().intValue()));
        l_strCorpCode.append(l_orderUnitRow.getOrderRequestNumber());
        l_strCorpCode.append(l_orderUnitRow.getConfirmedOrderRev());
        l_strCorpCode.append("999");

        log.exiting(STR_METHOD_NAME);
        return l_strCorpCode.toString();
    }

    /**
     * (get�t�����g����������敪�R�[�h)<BR>
     * �����̎s��R�[�h���A�t�����g����������敪�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̎s��R�[�h��"1"�i���؁j �̏ꍇ<BR>
     * �@@�@@�����̎s��R�[�h�̒l�����̂܂ܕԂ��B<BR>
     * <BR>
     * �Q�j�@@�����̎s��R�[�h��"2"�i��؁j �̏ꍇ<BR>
     * �@@�@@�����̎s��R�[�h�̒l�����̂܂ܕԂ��B<BR>
     * <BR>
     * �R�j�@@�����̎s��R�[�h����L�ȊO�̏ꍇ<BR>
     * �@@�@@null��Ԃ��B<BR>
     * <BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * Web�V�̎s��R�[�h�B
     * @@return String
     */
    public String getFrontOrderExchangeCode(String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "getFrontOrderExchangeCode(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����̎s��R�[�h��"1"�i���؁j�̏ꍇ
        //  �@@�@@�����̎s��R�[�h�̒l�����̂܂ܕԂ��B
        if (WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
        {
            log.exiting(STR_METHOD_NAME);
            return l_strMarketCode;
        }
        //  �Q�j�@@�����̎s��R�[�h��"2"�i��؁j�̏ꍇ
        //  �@@�@@�����̎s��R�[�h�̒l�����̂܂ܕԂ��B
        else if (WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
        {
            log.exiting(STR_METHOD_NAME);
            return l_strMarketCode;
        }
        //  �R�j�@@�����̎s��R�[�h����L�ȊO�̏ꍇ
        //  �@@�@@null��Ԃ��B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get������ؑ�)<BR>
     * �w��̒����P�ʂɍ��v���锭����ؑփI�u�W�F�N�g���擾���Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.�����o�H�敪 == "9�F������~"�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@this.get�t�����g�����V�X�e���敪()�ɂāA�t�����g�����V�X�e���敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@��get�t�����g�����V�X�e���敪()�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̒����P��.�s��ID�ɊY������s��R�[�h<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * <BR>
     * �R�j�@@������ؑփN���X���C���X�^���X������B <BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@��������ؑփN���X�F�R���X�g���N�^�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����̒����P��.���XID�ɊY�����镔�X.�،���ЃR�[�h <BR>
     * �@@�@@�@@�����^�C�v�F�@@"�敨�I�v�V����" <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̒����P��.�s��ID�ɊY������s��R�[�h <BR>
     * �@@�@@�@@�����o�H�敪�F�@@�����̒����P��.�����o�H�敪 <BR>
     * �@@�@@�@@�t�����g�����V�X�e���敪�F�@@�Q�j�Ŏ擾�����t�����g�����V�X�e���敪 <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * <BR>
     * �S�j�@@������ؑփN���X�̃C���X�^���X��ԋp����B <BR>
     * �@@�@@�@@�R�j�ŊY������f�[�^�����݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return ������ؑ�
     * @@throws WEB3BaseException
     */
    public WEB3GentradeOrderSwitching getOrderSwitching(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderSwitching(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@�����̒����P��.�����o�H�敪=="9�F������~"�̏ꍇ�́Anull��ԋp����B
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_orderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@this.get�t�����g�����V�X�e���敪()�ɂāA�t�����g�����V�X�e���敪���擾����B
        //�@@�@@�@@------------------------------------------------
        //�@@�@@�@@��get�t�����g�����V�X�e���敪()�F�����ݒ�d�l��
        //
        //�@@�@@�@@�s��R�[�h�F�@@�����̒����P��.�s��ID�ɊY������s��R�[�h
        //�@@�@@�@@------------------------------------------------
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        String l_strMarketCode = null;

        try
        {
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            l_strMarketCode = l_market.getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        String l_strFrontOrderSystemCode =
            this.getFrontOrderSystemCode(
                l_strMarketCode);

        //�R�j�@@������ؑփN���X���C���X�^���X������B
        //�@@�@@�@@------------------------------------------------
        //�@@�@@�@@��������ؑփN���X�F�R���X�g���N�^�����ݒ�d�l��
        //�@@�@@�@@�،���ЃR�[�h�F�@@�����̒����P��.���XID�ɊY�����镔�X.�،���ЃR�[�h
        //�@@�@@�@@�����^�C�v�F�@@"�敨�I�v�V����"
        //�@@�@@�@@�s��R�[�h�F�@@�����̒����P��.�s��ID�ɊY������s��R�[�h
        //�@@�@@�@@�����o�H�敪�F�@@�����̒����P��.�����o�H�敪
        //�@@�@@�@@�t�����g�����V�X�e���敪�F�@@�Q�j�Ŏ擾�����t�����g�����V�X�e���敪
        //�@@�@@�@@------------------------------------------------
        //�S�j�@@������ؑփN���X�̃C���X�^���X��ԋp����B
        //�@@�@@�@@�R�j�ŊY������f�[�^�����݂��Ȃ��ꍇ�́Anull��ԋp����B
        WEB3GentradeOrderSwitching l_orderSwitching = null;
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        String l_strInstitutionCode = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            l_strInstitutionCode = l_branchRow.getInstitutionCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        try
        {
            l_orderSwitching =
                new WEB3GentradeOrderSwitching(
                    l_strInstitutionCode,
                    ProductTypeEnum.IFO,
                    l_strMarketCode,
                    l_orderUnitRow.getSubmitOrderRouteDiv(),
                    l_strFrontOrderSystemCode);
        }
        catch (WEB3SystemLayerException l_sle)
        {
            if (!l_sle.getErrorInfo().equals(WEB3ErrorCatalog.SYSTEM_ERROR_80005))
            {
                throw l_sle;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderSwitching;
    }

    /**
     * (update�敨OP��������L���[AT��t���ԊO)<BR>
     * �O���t���ԊO�������Ĕ������邽�߂̃L���[�f�[�^�X�V���s���B<BR>
     * <BR>
     * �P�j�@@������is��� == true�i����j�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̏����ɍ��v���郌�R�[�h.�����敪��"������"�ɍX�V����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����敪 = "���"<BR>
     * �@@�@@���@@�Г��������� = this.get�i������j�Г���������(�����̒����P�ʁi�X�V�O�j)�̖߂�l<BR>
     * �@@�@@���@@�S���������敪 = "�S�����ȊO" <BR>
     * <BR>
     * �Q�j�@@������is��� == false�i����ȊO�j�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̏����ɍ��v���郌�R�[�h���X�V����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����敪 = "����ȊO"<BR>
     * �@@�@@���@@�Г��������� = this.get�Г���������(�����̒����P�ʁi�X�V�O�j)�̖߂�l<BR>
     * �@@�@@���@@�S���������敪 = "�S�����ȊO" <BR>
     * <BR>
     * �@@�@@[�X�V�Ώۍ���] <BR>
     * �@@�@@�@@�Г��������ځF�@@this.get�Г���������(�����̒����P�ʁi�X�V��j)�̖߂�l <BR>
     * �@@�@@�@@�����敪�F�@@"������" <BR>
     * <BR>
     * ��������̏ꍇ���X�V���t�����ݓ����ɍX�V����B<BR>
     * <BR>
     * @@param l_orderUnitAfter - (�����P�ʁi�X�V��j)<BR>
     * �X�V��̒����P�ʃI�u�W�F�N�g�B
     * @@param l_orderUnitBefore - (�����P�ʁi�X�V�O�j)<BR>
     * �X�V�O�̒����P�ʃI�u�W�F�N�g�B
     * @@param l_blnIsCancel - (is���)<BR>
     * ������ǂ����𔻒肷��t���O�B<BR>
     * �itrue�F����Afalse�F����ȊO�j
     * @@throws WEB3BaseException
     */
    public void updateHostFotypeOrderAllAtAcceptOvertime(
        IfoOrderUnit l_orderUnitAfter,
        IfoOrderUnit l_orderUnitBefore,
        boolean l_blnIsCancel) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateHostFotypeOrderAllAtAcceptOvertime(IfoOrderUnit, IfoOrderUnit, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitBefore == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            if (l_blnIsCancel)
            {
                //    �P�j�@@������is���==true�i����j�̏ꍇ�A
                //  �@@�@@�ȉ��̏����ɍ��v���郌�R�[�h.�����敪��"������"�ɍX�V����B
                //  �@@�@@[����]
                //  �@@�@@�@@����敪="���"
                //  �@@�@@���@@�Г���������=this.get�i������j�Г���������(�����̒����P�ʁi�X�V�O�j)�̖߂�l
                //  �@@�@@���@@�S���������敪="�S�����ȊO"
                String l_strWhere =
                    "cancel_div=? and corp_code=? and all_order_change_div=?";
                Object[] l_objWhere =
                {
                    WEB3CancelDivDef.CANCEL,
                    this.getOrgCorpCode(l_orderUnitBefore),
                    WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE
                };

                HashMap l_map = new HashMap();
                l_map.put("status", WEB3FrontOrderStatusDef.NOT_DEAL);
                l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                log.debug("GtlUtils.getSystemTimestamp()" + GtlUtils.getSystemTimestamp());
                l_processor.doUpdateAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_strWhere,
                    l_objWhere,
                    l_map);
            }
            else
            {
                //  �Q�j�@@������is���==false�i����ȊO�j�̏ꍇ�A
                //  �@@�@@�ȉ��̏����ɍ��v���郌�R�[�h���X�V����B
                //  �@@�@@[����]
                //  �@@�@@�@@����敪="����ȊO"
                //  �@@�@@���@@�Г���������=this.get�Г���������(�����̒����P�ʁi�X�V�O�j)�̖߂�l
                //  �@@�@@���@@�S���������敪="�S�����ȊO"
                //  �@@�@@[�X�V�Ώۍ���]
                //  �@@�@@�@@�Г��������ځF�@@this.get�Г���������(�����̒����P�ʁi�X�V��j)�̖߂�l
                //  �@@�@@�@@�����敪�F�@@"������"
                String l_strWhere =
                    "cancel_div=? and corp_code=? and all_order_change_div=?";

                Object[] l_objWhere =
                {
                    WEB3CancelDivDef.EXCEPT_CANCEL,
                    this.getCorpCode(l_orderUnitBefore),
                    WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE
                };

                HashMap l_map = new HashMap();
                l_map.put("corp_code", this.getCorpCode(l_orderUnitAfter));
                l_map.put("status", WEB3FrontOrderStatusDef.NOT_DEAL);
                l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                log.debug("GtlUtils.getSystemTimestamp()" + GtlUtils.getSystemTimestamp());
                l_processor.doUpdateAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_strWhere,
                    l_objWhere,
                    l_map);
            }
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getNext����Rev)<BR>
     * �����̒���Rev��1���Z�����l��Ԃ��B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����̒���Rev��1���Z����B<BR>
     * <BR>
     * �Q�j�@@����Rev�̌������擾����B<BR>
     * �@@�@@this.get����Rev����() <BR>
     * <BR>
     * �R�j�@@�Z�o��������Rev������Rev�̌����𒴂����ꍇ�́A<BR>
     * �@@�@@�u����Rev.�̒l���ő包���𒴉߁v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_02185<BR>
     * <BR>
     * @@param l_strOrderRev - (����Rev)<BR>
     * @@return String
     * @@throws WEB3BusinessLayerException
     */
    public String getNextOrderRev(String l_strOrderRev) throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "getNextOrderRev(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����̒���Rev��1���Z����B
        //�Q�j�@@����Rev�̌������擾����B
        //�@@�@@this.get����Rev����()
        //�R�j�@@�Z�o��������Rev������Rev�̌����𒴂����ꍇ�́A
        //�@@�@@�u����Rev.�̒l���ő包���𒴉߁v�̗�O��throw����B
        int l_intNextOrderRev = Integer.parseInt(l_strOrderRev) + 1;
        String l_strNextOrderRev = Integer.toString(l_intNextOrderRev);
        int l_intMaxOrderRevLen = this.getFigureOfOrderRev();
        int l_intNextOrderRevLen = l_strNextOrderRev.length();

        if (l_intNextOrderRevLen > l_intMaxOrderRevLen)
        {
            log.debug("����Rev.�̒l���ő包���𒴉߁B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        StringBuffer l_strBufNextOrderRev = new StringBuffer();
        for (int i = l_intNextOrderRevLen; i < l_intMaxOrderRevLen; i++)
        {
            l_strBufNextOrderRev.append("0");
        }

        l_strBufNextOrderRev.append(l_strNextOrderRev);

        log.exiting(STR_METHOD_NAME);
        return l_strBufNextOrderRev.toString();
    }
}
@
