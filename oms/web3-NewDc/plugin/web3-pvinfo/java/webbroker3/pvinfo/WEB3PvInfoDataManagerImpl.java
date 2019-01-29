head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDataManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ(break)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2005/08/16 ��(SRA) ���捞No.016(�p�t�H�[�}���X���P)
Revesion History : 2005/10/07 杊��](���u) �d�l�ύXNo.056
Revesion History : 2005/10/13 ��c(SRA) ��Q�Ǘ�No.U2469(�s��J�ǎ��ԑю擾�̕s�)
Revesion History : 2005/10/31 ��c(SRA) ��Q�Ǘ�No.U2480(�\�����eParams�擾���ɂ�����A�\�����eID�ݒ�̕s��)
Revesion History : 2006/01/17 ��c(SRA) �d�l�ύXNo.061
Revesion History : 2006/05/22 �юu��(���u) �d�l�ύX ���f��063 065�@@
Revesion History : 2006/09/12 �����F(���u) �d�l�ύX���f��070
Revesion History : 2007/02/26 ����(���u) �d�l�ύX���f��073
Revesion History : 2007/03/13 ����(���u) �d�l�ύX���f��075
Revesion History : 2007/03/16 ����(���u) �d�l�ύX���f��076
Revesion History : 2007/04/19 ����(���u) �d�l�ύX���f��077
Revision History : 2007/07/13 �Ӑ�(���u) �d�l�ύX���f��083
Revision History : 2007/12/07 �И���(���u) �d�l�ύX���f��095,096
Revision History : 2008/01/08 �И���(���u) �d�l�ύX���f��097
Revision History : 2008/04/15 ���g(���u) �d�l�ύX���f��103
Revision History : 2008/05/14 �g�C��(���u) �d�l�ύX���f��104
Revision History : 2008/10/06 �đo�g(���u) �d�l�ύX���f��106�A���f��111
*/
package webbroker3.pvinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductPK;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.accountinfo.data.CommCampaignAccHistoryRow;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.accountinfo.data.CommCampaignProductMstRow;
import webbroker3.accountinfo.define.WEB3AccInfoRegistTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AdditionalDepositStopDef;
import webbroker3.common.define.WEB3CategCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OnlyMobileOpenDivDef;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.common.define.WEB3PvInfoDeleteFlagDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoReadFlagDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3ValidDivDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.gentrade.data.SecurityShortageAccountParams;
import webbroker3.gentrade.data.SecurityShortageAccountRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductDao;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.pvinfo.data.BrowseHistoryDao;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.BrowseHistoryRow;
import webbroker3.pvinfo.data.DisplayConditionParams;
import webbroker3.pvinfo.data.DisplayConditionRow;
import webbroker3.pvinfo.data.DisplayContentsDao;
import webbroker3.pvinfo.data.DisplayContentsPK;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.data.DisplayContentsRow;
import webbroker3.pvinfo.define.WEB3PvInfoProductDivDef;
import webbroker3.pvinfo.define.WEB3PvInfoTPBizDateCheckNumDef;
import webbroker3.pvinfo.define.WEB3PvInfoTradingPowerInfoDef;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginParams;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginRow;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (��ײ�ްĲ�̫Ұ����ް��Ȱ�ެImpl)<BR>
 * ��ײ�ްĲ�̫Ұ��݂�DB I/O�Ȃǂ��Ǘ�����N���X�B(�����N���X)<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoDataManagerImpl implements WEB3PvInfoDataManager
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoDataManagerImpl.class);

    /**
     * (get��������)<BR>
     * �����̏��i�敪�̒����������擾����B<BR>
     * <BR>
     * �P�j�p�����[�^.���i�敪�ɂ��A�����Ώۂ̃e�[�u���������肷��B<BR>
     * <BR>
     * �@@[�p�����[�^.���i�敪 == ("0�F����" or "1�F�M�p")�̏ꍇ]<BR>
     * �@@�@@�e�[�u���� = ���������P�ʃe�[�u��(eqtype_order_unit)<BR>
     * <BR>
     * �@@[�p�����[�^.���i�敪 == ("2�F�敨 " or "3�F�I�v�V����")�̏ꍇ]<BR>
     * �@@�@@�e�[�u���� = �敨OP�����P�ʃe�[�u��(ifo_order_unit)<BR>
     * <BR>
     * �@@[�p�����[�^.���i�敪 == ("4�F�O����")�̏ꍇ]<BR>
     * �@@�@@�e�[�u���� = �O�������P�ʃe�[�u��(feq_order_unit)<BR>
     * <BR>
     * �Q�j�p�����[�^.is���������ɂ��A����/�����������擾���������<BR>
     * �@@�@@�p�����[�^.��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@[�p�����[�^.is�������� == true�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�������������� += " and biz_date = ?"<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�������������� += " and biz_date > ?"<BR>
     * <BR>
     * �R�j�Ɩ����t(*1)�𕶎���ϊ�(�t�H�[�}�b�g�FyyyyMMdd)���A<BR>
     * �@@�p�����[�^.���������f�[�^�R���e�i�ɒǉ�����B<BR>
     * <BR>
     * �S�j���i�𔻕ʂ���������p�����[�^.��������������&<BR>
     * �@@�@@�p�����[�^.���������f�[�^�R���e�i�ɒǉ�����B<BR>
     * <BR>
     * �@@�p�����[�^.���i�敪���A<BR>
     * �@@["0�F����"�̏ꍇ]<BR>
     * �@@�@@�E�p�����[�^.�������������� += "and order_categ = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_type not in (?, ?)"<BR>
     * �@@�@@�E�p�����[�^.���������f�[�^�R���e�i�Ɉȉ��̒l��ǉ��B<BR>
     * �@@�@@�@@�@@�E"1�F��������"<BR>
     * �@@�@@�@@�@@�E"101�F�����~�j����������"<BR>
     * �@@�@@�@@�@@�E"102�F�����~�j����������"<BR>
     * <BR>
     * �@@["1�F�M�p"�̏ꍇ]<BR>
     * �@@�@@�E�p�����[�^.�������������� += "and order_categ != ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_type not in (?, ?)"<BR>
     * �@@�@@�E�p�����[�^.���������f�[�^�R���e�i�Ɉȉ��̒l��ǉ��B<BR>
     * �@@�@@�@@�@@�E"1�F��������"<BR>
     * �@@�@@�@@�@@�E"101�F�����~�j����������"<BR>
     * �@@�@@�@@�@@�E"102�F�����~�j����������"<BR>
     * <BR>
     * �@@["2�F�敨"�̏ꍇ]<BR>
     * �@@�@@�E�p�����[�^.�������������� += "and future_option_div = ? "<BR>
     * �@@�@@�E�p�����[�^.���������f�[�^�R���e�i��"1�F�敨"(�敨�^�I�v�V�����敪)��<BR>
     * �ǉ��B<BR>
     * <BR>
     * �@@["3�F�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�E�p�����[�^.�������������� += "and future_option_div = ? "<BR>
     * �@@�@@�E�p�����[�^.���������f�[�^�R���e�i��"2�F�I�v�V����"(�敨�^�I�v�V�����敪)<BR>
     * ��ǉ��B<BR>
     * <BR>
     * �@@["4�F�O����"�̏ꍇ]<BR>
     * �@@�@@�E�ǉ������Ȃ�<BR>
     * <BR>
     * �T�jQueryProcessor.doGetCountQuery()�ɂāA�����������擾����B<BR>
     * <BR>
     * �@@[doGetCountQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�P�j�ɂĎ擾�����e�[�u����<BR>
     * �@@arg1�F�@@��L������ǉ������p�����[�^.��������������<BR>
     * �@@arg2�F�@@��L������ǉ������p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �U�j�擾����������ԋp����B<BR>
     * <BR>
     * (*1)�Ɩ����t<BR>
     * �@@GtlUtils.getTradingSystem().getBizDate()�ɂĎ擾�����Ɩ����t<BR>
     * @@param l_strProductDiv - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 0�F�@@���� <BR>
     * 1�F�@@�M�p <BR>
     * 2�F�@@�敨 <BR>
     * 3�F�@@�I�v�V���� <BR>
     * 4�F�@@�O���� <BR>
     * <BR>
     * @@param l_isTodayOrder - (is��������)<BR>
     * �����������擾���邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@��������<BR>
     * true�F�@@��������<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_strQueryDataContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@return int
     * @@roseuid 4141331503A3
     */
    public int getOrderCnt(String l_strProductDiv, boolean l_isTodayOrder, String l_strQueryString, String[] l_strQueryDataContainers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderCnt(String, boolean, String, String[])";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.���i�敪���`�F�b�N����B
        if(!WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {
            log.error("���i�敪���s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���i�敪���s��");
        }
        
        //�p�����[�^.����������������`�F�b�N����B
        if (l_strQueryString == null)
        {
            log.error("�p�����[�^.��������������Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.��������������Null�o���Ȃ��B");
        }
        
        //�p�����[�^.���������f�[�^�R���e�i���`�F�b�N����B
        if(l_strQueryDataContainers == null)
        {
            log.error("�p�����[�^.���������f�[�^�R���e�iNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.���������f�[�^�R���e�i�o���Ȃ��B");
        }
                
        log.debug("���i�敪: " + l_strProductDiv);
        log.debug("is��������: " + l_isTodayOrder);
        log.debug("��������������: " + l_strQueryString);
        StringBuffer l_sbQueryString = new StringBuffer(l_strQueryString);
        
        List l_lisQueryVars = new ArrayList();
        for(int i = 0; i < l_strQueryDataContainers.length; i++)
        {
            l_lisQueryVars.add(l_strQueryDataContainers[i]);
        } 
        log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());
        
        //�P�j�p�����[�^.���i�敪�ɂ��A�����Ώۂ̃e�[�u���������肷��B
        RowType l_rowType = null;  
        //[�p�����[�^.���i�敪 == ("0�F����" or "1�F�M�p")�̏ꍇ 
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv) || 
            WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv))
        {
            l_rowType = EqtypeOrderUnitRow.TYPE;
        }
        //[�p�����[�^.���i�敪 == ("2�F�敨 " or "3�F�I�v�V����")�̏ꍇ]
        else if (WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv) || 
            WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv))
        {
            l_rowType = IfoOrderUnitRow.TYPE;
        }
        //[�p�����[�^.���i�敪 == ("4�F�O����")�̏ꍇ]
        else if (WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {
            l_rowType = FeqOrderUnitRow.TYPE;
        }
        
        //�Q�j�p�����[�^.is���������ɂ��A����/�����������擾����������p�����[�^.��������������ɒǉ�����B
        //[�p�����[�^.is�������� == true�̏ꍇ]
        if(l_isTodayOrder)
        {
            l_sbQueryString.append(" and biz_date = ? ");
        }
        //[��L�ȊO�̏ꍇ]
        else
        {
            l_sbQueryString.append(" and biz_date > ? ");
        }
        
        //�R�j�Ɩ����t(*1)�𕶎���ϊ�(�t�H�[�}�b�g�FyyyyMMdd)���A�p�����[�^.���������f�[�^�R���e�i�ɒǉ�����B
        //�Ɩ����t�擾 GtlUtils.getTradingSystem().getBizDate()�ɂĎ擾�����Ɩ����t
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        //�t�H�[�}�b�g�FyyyyMMdd
        l_datBizDate = WEB3DateUtility.toDay(l_datBizDate);
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");

        //�p�����[�^.���������f�[�^�R���e�i�ɒǉ�
        l_lisQueryVars.add(l_strBizDate);

        //�S�j���i�𔻕ʂ���������p�����[�^.��������������&�p�����[�^.���������f�[�^�R���e�i�ɒǉ�����B
        //    �p�����[�^.���i�敪���A
        //["0�F����"�̏ꍇ]
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv))
        {      
            l_sbQueryString.append(" and order_categ = ? and order_type not in (?, ?) ");
            
            //���������f�[�^�R���e�i�ɒǉ�
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }
        //["1�F�M�p"�̏ꍇ]
        else if (WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and order_categ != ? and order_type not in (?, ?) ");
            
            //���������f�[�^�R���e�i�ɒǉ�
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }     
        //["2�F�敨"�̏ꍇ]
        else if(WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and future_option_div = ? ");
            
            //���������f�[�^�R���e�i��"1�F�敨"(�敨�^�I�v�V�����敪)��ǉ��B
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.FUTURES);
            
        }
        //["3�F�I�v�V����"�̏ꍇ]
        else if(WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and future_option_div = ? ");
            
            //���������f�[�^�R���e�i��"2�F�I�v�V����"(�敨�^�I�v�V�����敪)��ǉ��B
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.OPTION);
        }
        //["4�F�O����"�̏ꍇ]
        else if(WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {

        }
        
        int l_intReturnRecordCnt = 0;
        //�T�jQueryProcessor.doGetCountQuery()�ɂāA�����������擾����B
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_lisQueryVars.size()];
            l_lisQueryVars.toArray(l_objVars);
            
            log.debug("��������������: " + l_sbQueryString.toString());
            log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());

            l_intReturnRecordCnt = l_queryProcessor.doGetCountQuery(l_rowType,
                l_sbQueryString.toString(),
                l_objVars);

            log.debug("QueryProcessor.doGetCountQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        
        //�U�j�擾����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        log.debug("��������: " + l_intReturnRecordCnt);
        return l_intReturnRecordCnt;
    }

    /**
     * (get��茏��)<BR>
     * �����̏��i�敪�̓�����茏�����擾����B<BR>
     * <BR>
     * �P�j�p�����[�^.���i�敪�ɂ��A�����Ώۂ̃e�[�u���������肷��B<BR>
     * <BR>
     * �@@[�p�����[�^.���i�敪 == ("0�F����" or "1�F�M�p")�̏ꍇ]<BR>
     * �@@�@@�e�[�u���� = ���������P�ʃe�[�u��(eqtype_order_unit)<BR>
     * <BR>
     * �@@[�p�����[�^.���i�敪 == ("2�F�敨 " or "3�F�I�v�V����")�̏ꍇ]<BR>
     * �@@�@@�e�[�u���� = �敨OP�����P�ʃe�[�u��(ifo_order_unit)<BR>
     * <BR>
     * �@@[�p�����[�^.���i�敪 == ("4�F�O����")�̏ꍇ]<BR>
     * �@@�@@�e�[�u���� = �O�������P�ʃe�[�u��(feq_order_unit)<BR>
     * <BR>
     * �Q�j��������������&���������f�[�^�R���e�i<BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@����ID = �p�����[�^.�ڋq.getAccountId()�@@����<BR>
     * �@@�@@������ = �Ɩ����t(*1)�@@����<BR>
     * �@@�@@��萔�� IS NOT null�@@����<BR>
     * �@@�@@��萔�� != 0<BR>
     * <BR>
     * �@@�Q�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = "account_id = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " biz_date = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "executed_quantity is not null and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "executed_quantity != to_number(?)) "<BR>
     * <BR>
     * �@@�Q�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.getAccountId()<BR>
     * �@@�@@�@@�E�Ɩ����t�@@��������ϊ�(�t�H�[�}�b�g�FyyyyMMdd)���A�Z�b�g<BR>
     * �@@�@@�@@�Enull<BR>
     * �@@�@@�@@�E"0"<BR>
     * <BR>
     * �R�j���i�𔻕ʂ����������������������&ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�p�����[�^.���i�敪���A<BR>
     * �@@["0�F����"�̏ꍇ]<BR>
     * �@@�@@�E�������������� += "and order_categ = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_type not in (?, ?)"<BR>
     * �@@�@@�EArrayList�Ɉȉ��̒l��ǉ��B<BR>
     * �@@�@@�@@�@@�E"1�F��������"<BR>
     * �@@�@@�@@�@@�E"101�F�����~�j����������"<BR>
     * �@@�@@�@@�@@�E"102�F�����~�j����������"<BR>
     * <BR>
     * �@@["1�F�M�p"�̏ꍇ]<BR>
     * �@@�@@�E�������������� += "and order_categ != ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_type not in (?, ?)"<BR>
     * �@@�@@�EArrayList�Ɉȉ��̒l��ǉ��B<BR>
     * �@@�@@�@@�@@�E"1�F��������"<BR>
     * �@@�@@�@@�@@�E"101�F�����~�j����������"<BR>
     * �@@�@@�@@�@@�E"102�F�����~�j����������"<BR>
     * <BR>
     * �@@["2�F�敨"�̏ꍇ]<BR>
     * �@@�@@�E�������������� += "and future_option_div = ? "<BR>
     * �@@�@@�EArrayList��"1�F�敨"(�敨�^�I�v�V�����敪)��ǉ��B<BR>
     * <BR>
     * �@@["3�F�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�E�������������� += "and future_option_div = ? "<BR>
     * �@@�@@�EArrayList��"2�F�I�v�V����"(�敨�^�I�v�V�����敪)��ǉ��B<BR>
     * <BR>
     * �@@["4�F�O����"�̏ꍇ]<BR>
     * �@@�@@�E�ǉ������Ȃ�<BR>
     * <BR>
     * �S�jQueryProcessor.doGetCountQuery()�ɂāA������茏�����擾����B<BR>
     * <BR>
     * �@@[doGetCountQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�P�j�ɂĎ擾�����e�[�u����<BR>
     * �@@arg1�F�@@�쐬������������������<BR>
     * �@@arg2�F�@@�쐬����ArrayList.toArray()<BR>
     * <BR>
     * �T�j�擾����������ԋp����B<BR>
     * <BR>
     * (*1)�Ɩ����t<BR>
     * �@@GtlUtils.getTradingSystem().getBizDate()�ɂĎ擾�����Ɩ����t<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strProductDiv - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 0�F�@@���� <BR>
     * 1�F�@@�M�p <BR>
     * 2�F�@@�敨 <BR>
     * 3�F�@@�I�v�V���� <BR>
     * 4�F�@@�O���� <BR>
     * 
     * @@return int
     * @@roseuid 414152F00384
     */
    public int getExecuteCnt(WEB3GentradeMainAccount l_mainAccount, String l_strProductDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExecuteCnt(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }

        //�p�����[�^.���i�敪���`�F�b�N����B
        if(!WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {
            log.error("���i�敪���s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���i�敪���s��");
        }
        
        log.debug("���i�敪: " + l_strProductDiv);
        //�P�j�p�����[�^.���i�敪�ɂ��A�����Ώۂ̃e�[�u���������肷��B
        RowType l_rowType = null;
        //[�p�����[�^.���i�敪 == ("0�F����" or "1�F�M�p")�̏ꍇ]
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv) ||
            WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv))
        {
            l_rowType = EqtypeOrderUnitRow.TYPE;
        }
        //[�p�����[�^.���i�敪 == ("2�F�敨 " or "3�F�I�v�V����")�̏ꍇ]
        else if (WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv) ||
            WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv))
        {
            l_rowType = IfoOrderUnitRow.TYPE;
        }
        //[�p�����[�^.���i�敪 == ("4�F�O����")�̏ꍇ]
        else if (WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {
            l_rowType = FeqOrderUnitRow.TYPE;
        }

        //�Q�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQueryString = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        l_sbQueryString.append("account_id = ? and ");
        l_sbQueryString.append(" biz_date = ? and ");
        l_sbQueryString.append("executed_quantity is not null and executed_quantity <> to_number(?)");
        

        l_lisQueryVars.add(new Long(l_mainAccount.getAccountId()));
        //�Ɩ����t�擾 GtlUtils.getTradingSystem().getBizDate()�ɂĎ擾�����Ɩ����t
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        //�t�H�[�}�b�g�FyyyyMMdd
        l_datBizDate = WEB3DateUtility.toDay(l_datBizDate);
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        l_lisQueryVars.add(l_strBizDate);
        l_lisQueryVars.add("0");
        
        //�R�j���i�𔻕ʂ����������������������&ArrayList�ɒǉ�����B
        //�p�����[�^.���i�敪���A
        //["0�F����"�̏ꍇ]
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and order_categ = ? and order_type not in (?, ?) ");
            
            //���������f�[�^�R���e�i�ɒǉ�
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }
        //["1�F�M�p"�̏ꍇ]
        else if (WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and order_categ != ? and order_type not in (?, ?) ");
            
            //���������f�[�^�R���e�i�ɒǉ�
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }     
        //["2�F�敨"�̏ꍇ]
        else if(WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and future_option_div = ? ");
            
            //���������f�[�^�R���e�i��"1�F�敨"(�敨�^�I�v�V�����敪)��ǉ��B
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.FUTURES);
            
        }
        //["3�F�I�v�V����"�̏ꍇ]
        else if(WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and future_option_div = ? ");
            
            //���������f�[�^�R���e�i��"2�F�I�v�V����"(�敨�^�I�v�V�����敪)��ǉ��B
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.OPTION);
        }
        //["4�F�O����"�̏ꍇ]
        else if(WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {

        }
            
        //�S�jQueryProcessor.doGetCountQuery()�ɂāA������茏�����擾����B
        int l_intReturnRecordCnt = 0;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_lisQueryVars.size()];
            l_lisQueryVars.toArray(l_objVars);

            log.debug("��������������: " + l_sbQueryString.toString());
            log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());

            l_intReturnRecordCnt = l_queryProcessor.doGetCountQuery(
                l_rowType,
                l_sbQueryString.toString(),
                l_objVars);

            log.debug("QueryProcessor.doGetCountQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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

        //�T�j�擾����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        log.debug("��茏��: " + l_intReturnRecordCnt);
        return l_intReturnRecordCnt;
    }

    /**
     * (get�\�����eParams)<BR>
     * �����̕\�����eID�ɊY������\�����eParams��<BR>
     * �\�����e�e�[�u������擾����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ɊY������\�����eParams���擾����B<BR>
     * �@@[����]<BR>
     * �@@�@@�\�����eID = �p�����[�^.�\�����eID<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�̌��ʂ�ԋp����B<BR>
     * @@param l_lngDisplayContentsId - (�\�����eID)<BR>
     * �\�����eID<BR>
     * @@return webbroker3.pvinfo.data.DisplayContentsParams
     * @@roseuid 4147A21F0069
     */
    public DisplayContentsParams getDisplayContentsParams(long l_lngDisplayContentsId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDisplayContentsParams(long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�\�����eID: " + l_lngDisplayContentsId);
        DisplayContentsRow l_dcRow = null;
        try
        {
            //�P�j�����̕\�����eID�ɊY������\�����eParams��\�����e�e�[�u������擾����B
            DisplayContentsPK l_dcPk = new DisplayContentsPK(l_lngDisplayContentsId);
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_dcRow = (DisplayContentsRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_dcPk);
            log.debug("QueryProcessor.doFindByPrimaryKeyQuery()�����s���܂�");
        }
        //null value return
        catch(DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            return null;
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
        
        //�Q�j�̌��ʂ�ԋp����B
        if (l_dcRow == null)
        {
            log.debug("�\�����eParams is NULL");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return (DisplayContentsParams)l_dcRow;
    }

    /**
     * (get�\�����eParams�ꗗ)<BR>
     * �����ɊY������\�����eParams�̈ꗗ��<BR>
     * �\�����e�e�[�u������擾����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�h�\�����e�e�[�u��(display_contents)�h<BR>
     * �@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@arg3�F�@@null<BR>
     * �@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�̌��ʂ�ԋp����B<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_strQueryDataContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return List
     * @@roseuid 4145092300D9
     */
    public List getDisplayContentsParamsList(
        String l_strQueryString,
        String[] l_strQueryDataContainers,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDisplayContentsParamsList(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.����������������`�F�b�N����B
        if (l_strQueryString == null)
        {
            log.error("�p�����[�^.��������������Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.��������������Null�o���Ȃ��B");
        }
        
        //�p�����[�^.���������f�[�^�R���e�i���`�F�b�N����B
        if(l_strQueryDataContainers == null)
        {
            log.error("�p�����[�^.���������f�[�^�R���e�iNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.���������f�[�^�R���e�i�o���Ȃ��B");
        }
        
        log.debug("��������������: " + l_strQueryString);
        log.debug("�\�[�g����: " + l_strSortCond);
        List l_lisDisplayContentsParams = null;
        try
        {
            //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDisplayContentsParams = l_queryProcessor.doFindAllQuery(
                DisplayContentsParams.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryDataContainers);

            log.debug("QueryProcessor.doFindAllQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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

        if (l_lisDisplayContentsParams.isEmpty())
        {
            log.debug("�\�����e�e�[�u���ɊY���f�[�^�Ȃ�");
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisDisplayContentsParams;
    }

    /**
     * (insert�\�����e)<BR>
     * �\�����e�e�[�u���ɐV�K�f�[�^����s�o�^����B<BR>
     * <BR>
     * �P�j�\�����eParams�𐶐�����B<BR>
     * <BR>
     * �Q�j���������\�����eParams�ɑ΂��A�ȉ��̃v���p�e�B�Z�b�g���s���B<BR>
     * <BR>
     * �@@�\�����eID	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�،���ЃR�[�h	= �Ǘ���.get�،���ЃR�[�h()<BR>
     * �@@���X�R�[�h	= �Ǘ���.get���X�R�[�h()<BR>
     * �@@�\�������ԍ�	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�D��敪	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\������From	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\������To	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\���^�C�g��	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\������	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\���F		= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�_�ŕ\���t���O	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@URL�w��		= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�ŏI�X�V�����\���t���O	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�L��/�����t���O	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\���}��	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�ŏI�X�V��	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�쐬���t	= ���ݎ���(*1)<BR>
     * �@@�X�V���t	= ���ݎ���<BR>
     * <BR>
     * �R�jQueryProcessor.doInsertQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[doInsertQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�Q�j�ɂăv���p�e�B�Z�b�g�����\�����eParams<BR>
     * <BR>
     * (*1)���ݎ���<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�<BR>
     * TIMESTAMP_TAG)<BR>
     * �@@�ɂĎ擾�������ݎ���<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@param l_displayContentsInfo - (�\�����e���)<BR>
     * �\�����e���I�u�W�F�N�g<BR>
     * @@roseuid 415BF49B0047
     */
    public void insertDisplayContents(
        WEB3Administrator l_administrator,
        WEB3PvInfoDisplayContentsUnit l_displayContentsInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertDisplayContents(WEB3Administrator, WEB3PvInfoDisplayContentsUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.�Ǘ��҂��`�F�b�N����B
        if(l_administrator == null)
        {
            log.error("�p�����[�^.�Ǘ���Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�Ǘ���Null�o���Ȃ��B");
        }
        
        //�p�����[�^.�\�����e�����`�F�b�N����B
        if(l_displayContentsInfo == null)
        {
            log.error("�p�����[�^.�\�����e���Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�\�����e���Null�o���Ȃ��B");
        }

        //�P�j�\�����eParams�𐶐�����B
        DisplayContentsParams l_dcParams = new DisplayContentsParams();

        //�Q�j���������\�����eParams�ɑ΂��A�v���p�e�B�Z�b�g���s���B
        //�\�����eID    = �p�����[�^.�\�����e���̓�������
        l_dcParams.setDisplayContentsId(Long.parseLong(l_displayContentsInfo.displayContentsId));
        log.debug("�\�����eID: " + l_dcParams.getDisplayContentsId());
        //�،���ЃR�[�h   = �Ǘ���.get�،���ЃR�[�h()
        l_dcParams.setInstitutionCode(l_administrator.getInstitutionCode());
        log.debug("�،���ЃR�[�h: " + l_dcParams.getInstitutionCode());
        //���X�R�[�h   = �Ǘ���.get���X�R�[�h()
        l_dcParams.setBranchCode(l_administrator.getBranchCode());
        log.debug("���X�R�[�h: " + l_dcParams.getBranchCode());
        //�\�������ԍ�    = �p�����[�^.�\�����e���̓�������
        l_dcParams.setConditionNo(l_displayContentsInfo.conditionNumber);
        log.debug("�\�������ԍ�: " + l_dcParams.getConditionNo());
        //�D��敪    = �p�����[�^.�\�����e���̓�������
        l_dcParams.setPriorityDiv(l_displayContentsInfo.priorityDiv);
        log.debug("�D��敪: " + l_dcParams.getPriorityDiv());
        //�\������From  = �p�����[�^.�\�����e���̓�������
        l_dcParams.setTermFrom(WEB3DateUtility.getDate(l_displayContentsInfo.listStartDate, "yyyyMMddHHmm"));
        log.debug("�\������From: " + l_dcParams.getTermFrom());
        //�\������To    = �p�����[�^.�\�����e���̓�������
        l_dcParams.setTermTo(WEB3DateUtility.getDate(l_displayContentsInfo.listEndDate, "yyyyMMddHHmm"));
        log.debug("�\������To: " + l_dcParams.getTermTo());
        //�\���^�C�g��    = �p�����[�^.�\�����e���̓�������
        l_dcParams.setDisplayTitle(l_displayContentsInfo.displayTitle);
        log.debug("�\���^�C�g��: " + l_dcParams.getDisplayTitle());
        //�\������  = �p�����[�^.�\�����e���̓�������
        l_dcParams.setDisplayMessage(l_displayContentsInfo.displayMessage);
        log.debug("�\������: " + l_dcParams.getDisplayMessage());
        //�\���F       = �p�����[�^.�\�����e���̓�������
        l_dcParams.setDisplayColor(l_displayContentsInfo.displayColor);
        log.debug("�\���F: " + l_dcParams.getDisplayColor());
        //�_�ŕ\���t���O   = �p�����[�^.�\�����e���̓�������
        l_dcParams.setBlinkDisplayFlag(l_displayContentsInfo.blinkDisplayFlag ?
            WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES : 
            WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_NO);
        log.debug("�_�ŕ\���t���O: " + l_dcParams.getBlinkDisplayFlag());
        //URL�w��     = �p�����[�^.�\�����e���̓�������
        l_dcParams.setDisplayUrl(l_displayContentsInfo.displayUrl);
        log.debug("URL�w��: " + l_dcParams.getDisplayUrl());
        //�ŏI�X�V�����\���t���O   = �p�����[�^.�\�����e���̓�������
        l_dcParams.setLastUpdateTimeDisplayFlag(l_displayContentsInfo.lastUpdateTimeDisplayFlag ?
            WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_NO :
            WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES);
        log.debug("�ŏI�X�V�����\���t���O: " + l_dcParams.getLastUpdateTimeDisplayFlag());
        //�L��/�����t���O  = �p�����[�^.�\�����e���̓�������
        l_dcParams.setEffectiveFlag(l_displayContentsInfo.effectiveFlag ?
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_NO :
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES);
        log.debug("�L��/�����t���O: " + l_dcParams.getEffectiveFlag());
        //�\���}��  = �p�����[�^.�\�����e���̓�������
        l_dcParams.setDisplayDevice(l_displayContentsInfo.displayDevice);
        log.debug("�\���}��: " + l_dcParams.getDisplayDevice());
        //�ŏI�X�V�� = �p�����[�^.�\�����e���̓�������
        l_dcParams.setLastUpdateMember(l_displayContentsInfo.lastUpdateMember);
        log.debug("�ŏI�X�V��: " + l_dcParams.getLastUpdateMember());
                
        Timestamp l_tsCurrent = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        
        //�쐬���t  = ���ݎ���
        l_dcParams.setCreatedTimestamp(l_tsCurrent);
        log.debug("�쐬���t: " + l_dcParams.getCreatedTimestamp());
        //�X�V���t  = ���ݎ���
        l_dcParams.setLastUpdatedTimestamp(l_tsCurrent);
        log.debug("�X�V���t: " + l_dcParams.getLastUpdatedTimestamp());
        
        //�R�jQueryProcessor.doInsertQuery()���\�b�h���R�[������B
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_dcParams);
            
            log.debug("QueryProcessor.doInsertQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�\�����e)<BR>
     * �����̕\�����eParams�ŕ\�����e�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�jQueryProcessor.doUpdateQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@"�\�����e�e�[�u��(display_contents)"<BR>
     * �@@�@@arg1�F�@@�p�����[�^.�\�����eParams<BR>
     * @@param l_displayContentsParams - (�\�����eParams)<BR>
     * �\�����eParams�I�u�W�F�N�g<BR>
     * @@roseuid 415BF49B0076
     */
    public void updateDisplayContents(DisplayContentsParams l_displayContentsParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateDisplayContents(DisplayContentsParams)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�\�����eParams���`�F�b�N����B
        if(l_displayContentsParams == null)
        {
            log.error("�p�����[�^.�\�����eParamsNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�\�����eParamsNull�o���Ȃ��B");
        }
        
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_displayContentsParams);

            log.debug("QueryProcessor.doUpdateQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete�\�����e)<BR>
     * �����̕\�����eID�ɊY������\�����e�e�[�u���̃f�[�^���폜����B<BR>
     * <BR>
     * �P�j�\�����e�e�[�u��PK�C���X�^���X�𐶐�����B<BR>
     *  ���\�����e�e�[�u��PK�N���X��DDL��莩����������B <BR>
     * <BR>
     * �@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�\�����eID�F�@@�p�����[�^.�\�����eID<BR>
     * <BR>
     * �Q�jQueryProcessor.doDeleteQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doDeleteQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�P�j�ɂč쐬�����\�����e�e�[�u��PK<BR>
     * @@param l_lngDisplayContentsId - (�\�����eID)<BR>
     * �\�����eID<BR>
     * @@roseuid 415D3043036B
     */
    public void deleteDisplayContents(long l_lngDisplayContentsId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteDisplayContents(long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�\�����eID: " + l_lngDisplayContentsId);
        //�P�j�\�����e�e�[�u��PK�C���X�^���X�𐶐�����B
        DisplayContentsPK l_dcPK = new DisplayContentsPK(l_lngDisplayContentsId);
        
        //�Q�jQueryProcessor.doDeleteQuery()���\�b�h���R�[������B
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteQuery(l_dcPK);

            log.debug("QueryProcessor.doDeleteQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�V�K�\�����eID)<BR>
     * �\�����eID��V�K�̔Ԃ��A�ԋp����B<BR>
     * <BR>
     * �P�j�\�����e�e�[�u��DAO.newPkValue()���\�b�h���R�[������B<BR>
     * �@@���\�����e�e�[�u��DAO�N���X��DDL��莩����������B <BR>
     * <BR>
     * �Q�j�P�j�̌��ʂ�ԋp����B<BR>
     * @@return long
     * @@roseuid 415C0F010281
     */
    public long getNewDisplayContentsId() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNewDisplayContentsId()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�����e�e�[�u��DAO.newPkValue()���\�b�h���R�[������B
        long l_lngDisplayContentsId;
        try
        {
            l_lngDisplayContentsId = DisplayContentsDao.newPkValue();

            log.debug("DisplayContentsDao.newPkValue()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        
        //�Q�j�P�j�̌��ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        log.debug("�V�K�\�����eID: " + l_lngDisplayContentsId);
        return l_lngDisplayContentsId;
    }

    /**
     * (get�\�������ݒ�Params�ꗗ)<BR>
     * �����ɊY������\�������ݒ�Params�̈ꗗ���擾����B<BR>
     * <BR>
     * �P�j��������������&���������f�[�^�R���e�i<BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = " institution_code = ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.�،���ЃR�[�h<BR>
     * <BR>
     * �Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@"�\�������ݒ�e�[�u��(display_condition)"<BR>
     * �@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@arg2�F�@@�쐬����ArrayList.toArray()<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �R�j�Q�j�̌������ʂ�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@return List
     * @@roseuid 415BE56D02E2
     */
    public List getDisplayConditionParamsList(String l_strInstitutionCode, String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDisplayConditionParamsList(String, String)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�،���ЃR�[�h���`�F�b�N����B
        if(l_strInstitutionCode == null)
        {
            log.error("�p�����[�^.�،���ЃR�[�hNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�،���ЃR�[�hNull�o���Ȃ��B");
        }
    
        //�p�����[�^.���X�R�[�h���`�F�b�N����B
        //TODO:this parameter defined but never used.
//        if(l_strBranchCode == null)
//        {
//            log.error("�p�����[�^.���X�R�[�hNull�o���Ȃ��B");
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BaseRuntimeException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80019,
//                this.getClass().getName() + STR_METHOD_NAME,
//                "�p�����[�^.���X�R�[�hNull�o���Ȃ��B");
//        }
        
        //�P�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        //[��������] �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        l_sbQuery.append(" institution_code = ? ");
        l_lisQueryVars.add(l_strInstitutionCode);
        
        //�Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        List l_lisResultParams = null;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("��������������: " + l_sbQuery.toString());
            log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());
            
            l_lisResultParams =l_queryProcessor.doFindAllQuery(
                DisplayConditionRow.TYPE,
                l_sbQuery.toString(),
                null,
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doFindAllQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        
        if (l_lisResultParams.isEmpty())
        {
            log.debug("�Y���f�[�^�Ȃ�");
            return null;     
        }

        //�R�j�Q�j�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisResultParams;
    }

    /**
     * (create�\���������ꗗ)<BR>
     * �_�C���N�g�w��������A�\���������̈ꗗ���쐬����B<BR>
     * <BR>
     * �P�jthis.get�\�������ݒ�Params�ꗗ()��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[get�\�������ݒ�Params�ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�Ǘ���.get�،���ЃR�[�h()<BR>
     * �@@�@@���X�R�[�h�F�@@null<BR>
     * <BR>
     * �@@�@@get�\�������ݒ�Params�ꗗ()�̖߂�l == null�̏ꍇ�A<BR>
     * �@@�@@null��ԋp����B<BR>
     * <BR>
     * �Q�jArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�P�j�̖߂�l�̗v�f(=�\�������ݒ�Params)�����ȉ��̏�����<BR>
     * �@@�J��Ԃ��B<BR>
     * <BR>
     * �@@�R�|�P�j�����Ώۂ̕\�������ݒ�Params.�\�������ԍ� != <BR>
     * �@@�@@�@@�@@�@@"0000�F�_�C���N�g�w��"�̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�@@�R�|�P�|�P�j�\���������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�R�|�P�|�Q�j�\���������I�u�W�F�N�g�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�\���������.�\�������ԍ� = �\�������ݒ�Params.�\�������ԍ�<BR>
     * �@@�@@�@@�\���������.�\���� = �\�������ݒ�Params.�\��������<BR>
     * <BR>
     * �@@�@@�R�|�P�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����\���������<BR>
     *       �I�u�W�F�N�g��ǉ�����B<BR>
     * <BR>
     * �S�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit[]
     * @@roseuid 415BFC23009C
     */
    public WEB3PvInfoDisplayConditionUnit[] createDisplayConditionList(WEB3Administrator l_administrator) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createDisplayConditionList(WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.�Ǘ��҂��`�F�b�N����B
        if(l_administrator == null)
        {
            log.error("�p�����[�^.�Ǘ���Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�Ǘ���Null�o���Ȃ��B");
        }

        //�P�jthis.get�\�������ݒ�Params�ꗗ()���R�[������B
        List l_lisDisplayContensParams;
        l_lisDisplayContensParams = getDisplayConditionParamsList(l_administrator.getInstitutionCode(), null);
        if (l_lisDisplayContensParams == null)
        {
            log.debug("this.get�\�������ݒ�Params�ꗗ() is NULL");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�Q�jArrayList�𐶐�����B
        List l_lisDisplayConditionUnit = new ArrayList();
        
        //�R�j�P�jLOOP
        int l_intParamsCnt = l_lisDisplayContensParams.size();
        for(int i = 0; i < l_intParamsCnt; i ++)
        {
            
            DisplayConditionParams l_params = (DisplayConditionParams)l_lisDisplayContensParams.get(i);
            //�R�|�P�j�����Ώۂ̕\�������ݒ�Params.�\�������ԍ� != "0000�F�_�C���N�g�w��"�̏ꍇ�A�ȉ��̏������s���B
            if(!WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_params.condition_no))
            {
                //�R�|�P�|�P�j�\���������I�u�W�F�N�g�𐶐�����B
                WEB3PvInfoDisplayConditionUnit l_dcUnit = new WEB3PvInfoDisplayConditionUnit();
                
                //�R�|�P�|�Q�j�\���������I�u�W�F�N�g�Ɉȉ��̃v���p�e�B���Z�b�g����B
                l_dcUnit.conditionName = l_params.condition_name;
                l_dcUnit.conditionNumber = l_params.condition_no;
                
                //�R�|�P�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����\���������I�u�W�F�N�g��ǉ�����B
                l_lisDisplayConditionUnit.add(l_dcUnit);
            }
        }
        
        //�S�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3PvInfoDisplayConditionUnit[] l_dcUnits = null;
        
        if (l_intParamsCnt > 0)
        {
            l_dcUnits = new WEB3PvInfoDisplayConditionUnit[l_lisDisplayConditionUnit.size()];
            l_lisDisplayConditionUnit.toArray(l_dcUnits); 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dcUnits;
    }

    /**
     * (get�{������Params)<BR>
     * �{������Params���擾����B<BR>
     * <BR>
     * �P�j�{�������e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = �p�����[�^.�ڋq.���X�R�[�h<BR>
     * �@@�\�����eID = �p�����[�^.�\�����eID<BR>
     * �@@�ڋq�R�[�h = �p�����[�^.�ڋq.getAccountCode()<BR>
     * �@@���ڋq�R�[�h�͐擪6byte�Ŕ�r���邱�ƁB<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�̌������ʂ�ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_lngDisplayContentsId - (�\�����eID)<BR>
     * �\�����eID<BR>
     * @@return webbroker3.pvinfo.data.BrowseHistoryParams
     * @@roseuid 414523FA032B
     */
    public BrowseHistoryParams getBrowseHistoryParams(
        WEB3GentradeMainAccount l_mainAccount,
        long l_lngDisplayContentsId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBrowseHistoryParams(WEB3GentradeMainAccount, long)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }
        
        log.debug("�\�����eID: " + l_lngDisplayContentsId);
        
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //���ڋq�R�[�h�͐擪6byte�Ŕ�r���邱�ƁB
        String l_strAccountCode = l_mainAccount.getAccountCode().substring(0, 6);
        
        //�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B
        BrowseHistoryRow l_bhRow = null;
        try
        {
            l_bhRow = BrowseHistoryDao.findRowByInstitutionCodeBranchCodeDisplayContentsIdAccountCode(
                l_strInstitutionCode,
                l_strBranchCode,
                l_lngDisplayContentsId,
                l_strAccountCode
                );

            log.debug("BrowseHistoryDao.findRowByInstitutionCodeBranchCodeDisplayContentsIdAccountCode()�����s���܂�");            
        }
        catch (DataFindException l_ex)
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
        
        //�Q�j�P�j�̌������ʂ�ԋp����B
        if (l_bhRow == null)
        {
            log.debug("get�{������Params is NULL");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return (BrowseHistoryParams) l_bhRow;
    }

    /**
     * (get�{������Params�ꗗ)<BR>
     * �����ɊY������{������Params�̈ꗗ��<BR>
     * �\�����e�e�[�u������擾����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�h�{�������e�[�u��(browse_history)�h<BR>
     * �@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@arg3�F�@@null<BR>
     * �@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�̌��ʂ�ԋp����B<BR>
     * @@param l_strSQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_strQueryDataContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return List
     * @@roseuid 415CBDF403C1
     */
    public List getBrowseHistoryParamsList(
        String l_strQueryString,
        String[] l_strQueryDataContainers,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBrowseHistoryParamsList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.����������������`�F�b�N����B
        if (l_strQueryString == null)
        {
            log.error("�p�����[�^.��������������Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.��������������Null�o���Ȃ��B");
        }
        
        //�p�����[�^.���������f�[�^�R���e�i���`�F�b�N����B
        if(l_strQueryDataContainers == null)
        {
            log.error("�p�����[�^.���������f�[�^�R���e�iNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.���������f�[�^�R���e�i�o���Ȃ��B");
        }
        
        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        List l_lisResultParams = null;
        try
        {        
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("��������������: " + l_strQueryString);
            log.debug("�\�[�g����: " + l_strSortCond);
            
            l_lisResultParams = l_queryProcessor.doFindAllQuery(
                BrowseHistoryRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryDataContainers);

            log.debug("QueryProcessor.doFindAllQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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

        if (l_lisResultParams.isEmpty())
        {
            log.debug("�Y���f�[�^�Ȃ�");
            return null;
        }

        //�Q�j�P�j�̌��ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisResultParams;
    }
    /**
     * (get�{������Params�ꗗ)<BR>
     * �����̏����ɊY������{������Params�̈ꗗ���擾����B <BR>
     * �P�j�ȉ��̌���������������쐬����B <BR>
     * �������������� = "institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and display_contents_id = ? " <BR>
     * <BR>
     * �Q�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A <BR>
     * ���X��������������������ɒǉ�����B <BR>
     * �p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B <BR>
     * �������������� += "and branch_code in (?,?,,,) " <BR>
     * <BR>
     * �R�j�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A <BR>
     * �ڋq��������������������ɒǉ�����B <BR>
     * �������������� += " and account_code like '?%'" <BR>
     * <BR>
     * �S�j����������"?"�ɃZ�b�g���錟�������f�[�^�R���e�i(�FString[])���쐬����B <BR>
     * ���������f�[�^�R���e�i�̗v�f�́A�ȉ��̏����ŃZ�b�g���邱�ƁB <BR>
     * �@@�p�����[�^.�،���ЃR�[�h <BR>
     * �A�p�����[�^.�\�����eID <BR>
     * �B�p�����[�^.���X�R�[�h�̑S�v�f <BR>
     * �C�p�����[�^.�ڋq�R�[�h <BR>
     * ���B�A�C�ɂ��ẮA�Ή�����p�����[�^ != null�̏ꍇ�̂݃Z�b�g�B <BR>
     * ���C�ɂ��ẮA�p�����[�^.�ڋq�R�[�h.length() == 7byte�̏ꍇ�A�擪��6byte�݂̂��Z�b�g�B <BR>
     * <BR>
     * �T�jthis.get�{������Params�ꗗ()���\�b�h���R�[������B <BR>
     * [get�{������Params�ꗗ()�ɃZ�b�g����p�����[�^] <BR>
     * ��������������F�@@�쐬������������������ <BR>
     * ���������f�[�^�R���e�i�F�@@�쐬�������������f�[�^�R���e�i <BR>
     * �\�[�g�����F�@@null <BR>
     * �������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B <BR>
     * <BR>
     * �U�j�T�j�̌������ʂ�ԋp����B <BR>
     * @@param l_strInstitutionCode<BR>
     * @@param l_strBranchCode<BR>
     * @@param l_strAccountCode<BR>
     * @@param l_lngDisplayContentsId<BR>
     * @@return List
     * @@throws WEB3BaseException<BR>
     */
    public List getBrowseHistoryParamsList(String l_strInstitutionCode,String[] l_strBranchCode,String l_strAccountCode,long l_lngDisplayContentsId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBrowseHistoryParamsList(String, String[], String,long)";
        log.entering(STR_METHOD_NAME);
        if (l_strInstitutionCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //�P�j��̕�����𐶐�����B<BR>
        StringBuffer l_sbQueryString = new StringBuffer();
        //�P�j�ȉ��̌���������������쐬����B

        l_sbQueryString.append("institution_code = ? and display_contents_id = ?");
        //�Q�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A
        if (l_strBranchCode != null)
        {
            if (l_strBranchCode.length == 1)
            {
                l_sbQueryString.append(" and branch_code = ?");
            }
            
            if (l_strBranchCode.length > 1)
            {
                l_sbQueryString.append(" and (branch_code = ?");

                for (int i = 1; i < l_strBranchCode.length; i++)
                {
                    l_sbQueryString.append(" or branch_code = ?");
                }

                l_sbQueryString.append(")");
            }
        }
        
        //�R�j�p�����[�^.�ڋq�R�[�h != null�̏ꍇ
        if (l_strAccountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%'");
        }
        
        //�S�j����������"?"�ɃZ�b�g���錟�������f�[�^�R���e�i(�FString[])���쐬����B
        //���������f�[�^�R���e�i�̗v�f�́A�ȉ��̏����ŃZ�b�g���邱�ƁB 
        //���ArrayList�𐶐�����B<BR>
        List l_lstQueryContainer = new ArrayList();
        //�@@�p�����[�^.�،���ЃR�[�h
        l_lstQueryContainer.add(l_strInstitutionCode);
        //�p�����[�^.�\�����eID 
        l_lstQueryContainer.add(Long.toString(l_lngDisplayContentsId));
        //   ����.���X�R�[�h�̊e�v�f ���P�j��List�ɒǉ�����B<BR>
        //�B�p�����[�^.���X�R�[�h�̑S�v�f 

        if (l_strBranchCode != null)
        {
            if (l_strBranchCode.length > 0)
            {
                for (int i = 0; i < l_strBranchCode.length; i++)
                {
                    l_lstQueryContainer.add(l_strBranchCode[i]);
                }
            }
        }

        //�C�p�����[�^.�ڋq�R�[�h 
        if (l_strAccountCode !=null)
        {
            int l_intAcountCode = WEB3StringTypeUtility.getByteLength(l_strAccountCode);
            if (l_intAcountCode == 7)
            {
                l_lstQueryContainer.add(l_strAccountCode.substring(0,6));
            }
            else
            {

                l_lstQueryContainer.add(l_strAccountCode);
            }
        }

        
        //�������ꂽList����z����擾���A�ԋp����B
        String[] l_queryContainer = new String[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);
        //�T�jthis.get�{������Params�ꗗ()���\�b�h���R�[������B
        List l_listParamsList = this.getBrowseHistoryParamsList(l_sbQueryString.toString(),l_queryContainer,null);
        return l_listParamsList;
    }
    /**
     * (insert�{������)<BR>
     * �{�������e�[�u���Ƀf�[�^����s�o�^����B<BR>
     * <BR>
     * �P�j�{������Params�𐶐�����B<BR>
     * <BR>
     * �Q�j��������Params�ɑ΂��A�ȉ��̃v���p�e�B�Z�b�g���s���B<BR>
     * <BR>
     * �@@�{������ID = �V�K�̔Ԃ���ID(*1)<BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = �p�����[�^.���X�R�[�h<BR>
     * �@@�\�����eID = �p�����[�^.�\�����eID<BR>
     * �@@�ڋq�R�[�h = �p�����[�^.�ڋq�R�[�h<BR>
     * �@@���Ǌ��ǃt���O = <BR>
     * �@@�@@[�p�����[�^.is���� == false�̏ꍇ]<BR>
     * �@@�@@�@@"0�F����"���Z�b�g�B<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@"1�F����"���Z�b�g�B<BR>
     * �@@�폜�t���O = "0�F���폜"<BR>
     *  �ŏI�{������ = <BR>
     *      [�p�����[�^.is���� == false�̏ꍇ]<BR>
     *      null���Z�b�g�B<BR>
     *      [��L�ȊO�̏ꍇ]<BR>
     *      ���ݎ���(*2)���Z�b�g�B<BR>
     *  �쐬���t = ���ݎ���<BR>
     *  �X�V���t = ���ݎ��� <BR>
     * <BR>
     * �R�jQueryProcessor.doInsertQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doInsertQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�Q�j�ɂăv���p�e�B�Z�b�g�����{������Params<BR>
     * <BR>
     * �@@(*1)�@@�{�������h�c�V�K�̔� <BR>
     * �@@�{�������e�[�u��DAO.newPkValue()���\�b�h�ɂĎ擾����B <BR>
     * �@@���{�������e�[�u��DAO�N���X��DDL��莩����������B <BR>
     * <BR>
     * �@@(*2)�@@���ݎ���<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * (������ԊǗ�.TIMESTAMP_TAG)�ɂĎ擾�������ݎ���<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_lngDisplayContentsId - (�\�����eID)<BR>
     * �\�����eID<BR>
     * @@param l_isRead - (is����)<BR>
     * ���Ǌ��ǃt���O�����ǂœo�^���邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@���ǂœo�^<BR>
     * true�F�@@���ǂœo�^<BR>
     * @@roseuid 4160D8C4017D
     */
    public void insertBrowseHistory(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        long l_lngDisplayContentsId,
        boolean l_isRead) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertBrowseHistory(String, String, String, long, boolean)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�،���ЃR�[�h���`�F�b�N����B
        if(l_strInstitutionCode == null)
        {
            log.error("�p�����[�^.�،���ЃR�[�hNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�،���ЃR�[�hNull�o���Ȃ��B");
        }
    
        //�p�����[�^.���X�R�[�h���`�F�b�N����B
        if(l_strBranchCode == null)
        {
            log.error("�p�����[�^.���X�R�[�hNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.���X�R�[�hNull�o���Ȃ��B");
        }
        
        //�p�����[�^.�ڋq�R�[�h���`�F�b�N����B
        if(l_strAccountCode == null)
        {
            log.error("�p�����[�^.�ڋq�R�[�hNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋq�R�[�hNull�o���Ȃ��B");
        }
        
// *** ���捞No.016 START            
        // �ڋq���擾����B
        MainAccount l_mainAccount = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_mainAccount = l_accManager.getMainAccount(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode);
        long l_lngAccountId = l_mainAccount.getAccountId();
// *** ���捞No.016 END            
        
        log.debug("�،���ЃR�[�h: " + l_strInstitutionCode);
        log.debug("���X�R�[�h: " + l_strBranchCode);
        log.debug("�ڋq�R�[�h: " + l_strAccountCode);
        log.debug("�\�����eID: " + l_lngDisplayContentsId);
        log.debug("is����: " + l_isRead);
// *** ���捞No.016 START            
        log.debug("����ID: " + l_lngAccountId);
// *** ���捞No.016 END            

        //�P�j�{������Params�𐶐�����B
        BrowseHistoryParams l_bhParams = new BrowseHistoryParams();
        
        //�Q�j��������Params�ɑ΂��A�ȉ��̃v���p�e�B�Z�b�g���s���B
        try
        {
            l_bhParams.setBrowseHistoryId(BrowseHistoryDao.newPkValue());
            l_bhParams.setInstitutionCode(l_strInstitutionCode);
            l_bhParams.setBranchCode(l_strBranchCode);
            l_bhParams.setDisplayContentsId(l_lngDisplayContentsId);
            l_bhParams.setAccountCode(l_strAccountCode);
// *** ���捞No.016 START            
            l_bhParams.setAccountId(l_lngAccountId);
// *** ���捞No.016 END            
            l_bhParams.setReadFlag(l_isRead ? WEB3PvInfoReadFlagDef.READ_YES : WEB3PvInfoReadFlagDef.READ_NO);
            l_bhParams.setDeleteFlag(WEB3PvInfoDeleteFlagDef.DELETE_NO);
            log.debug("�폜�t���O: " + l_bhParams.getDeleteFlag());
            //ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)�ɂĎ擾�������ݎ���
            Timestamp l_tsCurrent = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            //[�p�����[�^.is���� == false�̏ꍇ]     
            if (l_isRead == false)
            {
                //�ŏI�{������ = null���Z�b�g�B
                l_bhParams.setLastReadTimestamp(null);
            }
            //[��L�ȊO�̏ꍇ] ���ݎ���(*2)���Z�b�g�B
            else
            {
                l_bhParams.setLastReadTimestamp(l_tsCurrent);
            }
                       
            l_bhParams.setCreatedTimestamp(l_tsCurrent);
            log.debug("�쐬���t: " + l_bhParams.getCreatedTimestamp());
            l_bhParams.setLastUpdatedTimestamp(l_tsCurrent);
            log.debug("�X�V���t: " + l_bhParams.getLastUpdatedTimestamp());
            
            //�R�jQueryProcessor.doInsertQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_bhParams);

            log.debug("QueryProcessor.doInsertQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�{������)<BR>
     * �����̉{������Params�ŉ{�������e�[�u�����X�V����B<BR>
     * <BR>
     * �P�jQueryProcessor.doUpdateQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@"�{�������e�[�u��(browse_history)"<BR>
     * �@@�@@arg1�F�@@�p�����[�^.�{������Params<BR>
     * @@param l_browseHistoryParams - (�{������Params)<BR>
     * �{������Params�I�u�W�F�N�g<BR>
     * @@roseuid 4145236D005C
     */
    public void updateBrowseHistory(BrowseHistoryParams l_browseHistoryParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateBrowseHistory(BrowseHistoryParams)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�{������Params���`�F�b�N����B
        if(l_browseHistoryParams == null)
        {
            log.error("�p�����[�^.�{������ParamsNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�{������ParamsNull�o���Ȃ��B");
        }
        
        try
        {
            //1 �jQueryProcessor.doInsertQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_browseHistoryParams);

            log.debug("QueryProcessor.doUpdateQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete�{������)<BR>
     * �����̕\�����eID�ɊY������{�������e�[�u���̃f�[�^���폜����B<BR>
     * <BR>
     * �P�j��������������&���������f�[�^�R���e�i<BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�\�����eID = �p�����[�^.�\�����eID<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = " display_contents_id = ?"<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���Z�b�g����B<BR>
     * �@@�@@��������ϊ����ăZ�b�g���邱�ƁB<BR>
     * �@@�@@�@@�E�p�����[�^.�\�����eID<BR>
     * <BR>
     * �Q�jQueryProcessor.doDeleteQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doDeleteQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@"�{�������e�[�u��(browse_history)<BR>
     * �@@�@@arg1�F�@@�P�j�ɂč쐬������������������<BR>
     * �@@�@@arg2�F�@@�P�j�ɂč쐬����ArrayList.toArray()<BR>
     * @@param l_lngDisplayContentsId - (�\�����eID)<BR>
     * �\�����eID<BR>
     * @@roseuid 4160E9200090
     */
    public void deleteBrowseHistory(long l_lngDisplayContentsId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteBrowseHistory(long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�\�����eID: " + l_lngDisplayContentsId);
        
        //�P�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        //[��������] �P�|�P�j������������ɁA����������������쐬����B
        l_sbQuery.append("display_contents_id = ?");
        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        l_lisQueryVars.add(new Long(l_lngDisplayContentsId));
        
        try
        {
            //�Q�jQueryProcessor.doDeleteQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("��������������: " + l_sbQuery.toString());
            log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());
            
            l_queryProcessor.doDeleteAllQuery(
                BrowseHistoryRow.TYPE,
                l_sbQuery.toString(),
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doDeleteAllQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete�{������)<BR>
     * �����̌ڋq���A�\�����eID�ɊY������{�������e�[�u���̃f�[�^��<BR>
     * �폜����B<BR>
     * <BR>
     * �P�j��������������&���������f�[�^�R���e�i<BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h ����<BR>
     * �@@�@@���X�R�[�h = �p�����[�^.���X�R�[�h ����<BR>
     * �@@�@@�ڋq�R�[�h = �p�����[�^.�ڋq�R�[�h ����<BR>
     * �@@�@@�\�����eID = �p�����[�^.�\�����eID<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = "institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and branch_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and account_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and display_contents_id = ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���Z�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.���X�R�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq�R�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.�\�����eID<BR>
     * <BR>
     * �Q�jQueryProcessor.doDeleteQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doDeleteQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@"�{�������e�[�u��(browse_history)<BR>
     * �@@�@@arg1�F�@@�P�j�ɂč쐬������������������<BR>
     * �@@�@@arg2�F�@@�P�j�ɂč쐬����ArrayList.toArray()<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_lngDisplayContentsId - (�\�����eID)<BR>
     * �\�����eID<BR>
     * @@roseuid 41610CD7028C
     */
    public void deleteBrowseHistory(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        long l_lngDisplayContentsId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteBrowseHistory(String, String, String, long)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�،���ЃR�[�h���`�F�b�N����B
        if(l_strInstitutionCode == null)
        {
            log.error("�p�����[�^.�،���ЃR�[�hNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�،���ЃR�[�hNull�o���Ȃ��B");
        }
    
        //�p�����[�^.���X�R�[�h���`�F�b�N����B
        if(l_strBranchCode == null)
        {
            log.error("�p�����[�^.���X�R�[�hNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.���X�R�[�hNull�o���Ȃ��B");
        }
        
        //�p�����[�^.�ڋq�R�[�h���`�F�b�N����B
        if(l_strAccountCode == null)
        {
            log.error("�p�����[�^.�ڋq�R�[�hNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋq�R�[�hNull�o���Ȃ��B");
        }

        log.debug("�،���ЃR�[�h: " + l_strInstitutionCode);
        log.debug("���X�R�[�h: " + l_strBranchCode);
        log.debug("�ڋq�R�[�h: " + l_strAccountCode);
        log.debug("�\�����eID: " + l_lngDisplayContentsId);

        //�P�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        
        //[��������] �P�|�P�j������������ɁA����������������쐬����B
        l_sbQuery.append("institution_code = ? ");
        l_sbQuery.append("and branch_code = ?  ");
        l_sbQuery.append("and account_code = ?  ");
        l_sbQuery.append("and display_contents_id = ? ");

        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        l_lisQueryVars.add(l_strInstitutionCode);
        l_lisQueryVars.add(l_strBranchCode);
        l_lisQueryVars.add(l_strAccountCode);
        l_lisQueryVars.add(new Long(l_lngDisplayContentsId));
        
        try
        {
            //�Q�jQueryProcessor.doDeleteQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("��������������: " + l_sbQuery.toString());
            log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());
            
            l_queryProcessor.doDeleteAllQuery(
                BrowseHistoryRow.TYPE,
                l_sbQuery.toString(),
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doDeleteAllQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getIPO�\��Params)<BR>
     * ���I����IPO�\��Params���擾����B<BR>
     * <BR>
     * �P�j��������������&���������f�[�^�R���e�i<BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@���XID = �p�����[�^.�ڋq.���XID�@@����<BR>
     * �@@�@@����ID = �p�����[�^.�ڋq.����ID�@@����<BR>
     * �@@�@@�⏕����ID = �⏕����.getSubAccountId()(*1)�@@����<BR>
     * �@@�@@(���I���� = "1�F���I"�@@�܂���<BR>
     * �@@�@@���I����(�J��) = "1�F���I")<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = " branch_id = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "account_id = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "sub_account_id = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(lot_result = ? or "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "lot_result_retry = ?) "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B<BR>
     * �@@�@@��������ϊ����ăZ�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.���XID<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.����ID<BR>
     * �@@�@@�@@�E�⏕����.getSubAccountId()<BR>
     * �@@�@@�@@�E"1�F���I"<BR>
     * �@@�@@�@@�E"1�F���I"<BR>
     * <BR>
     * �Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�hIPO�\���e�[�u��(ipo_order)�h<BR>
     * �@@arg1�F�@@�쐬������������������<BR>
     * �@@arg2�F�@@�쐬����ArrayList.toArray()<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �R�j�Q�j�̌��ʂ�ԋp����B<BR>
     * <BR>
     * (*1)�⏕����<BR>
     * �@@�p�����[�^.�ڋq.getSubAccount()�ɂĎ擾�B<BR>
     * <BR>
     * �@@[getSubAccount()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�⏕�����^�C�v�F�@@SubAccountTypeEnum.�����������<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return List
     * @@roseuid 41454EEC000E
     */
    public List getIpoOrderParams(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIpoOrderParams(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }

        List l_lisResultParams = null;
        //�P�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        try
        {        
            //[��������]�P�|�P�j������������ɁA����������������쐬����B
            l_sbQuery.append(" branch_id = ? and ");
            l_sbQuery.append("account_id = ? and ");
            l_sbQuery.append("sub_account_id = ? and ");
            l_sbQuery.append("(lot_result = ? or ");
            l_sbQuery.append("lot_result_retry = ?) ");
        
            //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
            l_lisQueryVars.add(new Long(l_mainAccount.getBranch().getBranchId()));
            l_lisQueryVars.add(new Long(l_mainAccount.getAccountId()));
            l_lisQueryVars.add(new Long(l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT).getSubAccountId()));
            l_lisQueryVars.add(WEB3LotResultDef.ELECTION);
            l_lisQueryVars.add(WEB3LotResultRetryDef.ELECTION);
        
            //�Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();

            log.debug("��������������: " + l_sbQuery.toString());
            log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());

            l_lisResultParams = l_queryProcessor.doFindAllQuery(
                IpoOrderRow.TYPE,
                l_sbQuery.toString(),
                null,
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doFindAllQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        catch (NotFoundException l_ex) 
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
         
        if (l_lisResultParams.isEmpty())
        {
            log.debug("�Y���f�[�^�Ȃ�");
            return null;
        }
         
        //�R�j�Q�j�̌��ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisResultParams;
    }

    /**
     * (getIPO����Params)<BR>
     * ������IPO����ID�ɊY������w���\�����ؑO��<BR>
     * IPO����Params���擾����B<BR>
     * <BR>
     * �P�j��������������&���������f�[�^�R���e�i<BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@IPO����ID = �p�����[�^.IPO����ID�@@����<BR>
     * �@@�@@�w���\���I������(���Аݒ�) > ���ݎ���(*1)<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = " ipo_product_id = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@      �@@+ "offer_end_datetime > <BR>
     *                               to_date(?, 'YYYYMMDDHH24MISS') "<BR>
     * <BR>
     * �@@�Q�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B<BR>
     * �@@�@@��������ϊ����ăZ�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.IPO����ID<BR>
     * �@@�@@�@@�E���ݎ����@@��������ϊ�(�t�H�[�}�b�g�FyyyyMMddHHmmss)���A�Z�b�g�B<BR>
     * <BR>
     * �Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�hIPO�����e�[�u��(ipo_product)�h<BR>
     * �@@arg1�F�@@�쐬������������������<BR>
     * �@@arg2�F�@@ArrayList.toArray()<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �R�j�Q�j�̌��ʂ�ԋp����B<BR>
     * <BR>
     * (*1)���ݎ���<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * (������ԊǗ�.TIMESTAMP_TAG)�ɂĎ擾�������ݎ���<BR>
     * @@param l_lngIpoProductId - (IPO����ID)<BR>
     * IPO����ID<BR>
     * @@param l_isAdvancedElection - (is�J�グ���I)<BR>
     * �J�グ���I���ǂ����̃t���O<BR>
     * false�F�@@�J�グ���I�łȂ�<BR>
     * true�F�@@�J�グ���I�ł���<BR>
     * @@return webbroker3.ipo.data.IpoProductParams
     * @@roseuid 41455DCC02AE
     */
    public IpoProductParams getIpoProductParams(long l_lngIpoProductId, boolean l_isAdvancedElection) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIpoProductParams(long)";
        log.entering(STR_METHOD_NAME);

        log.debug("IPO����ID: " + l_lngIpoProductId);

        //�P�j��������������&���������f�[�^�R���e�i
        //�Q�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        Timestamp l_tsCurrent = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        
        //�Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        IpoProductRow l_ipoProductRow = null;
        try
        {
            l_ipoProductRow = IpoProductDao.findRowByPk(l_lngIpoProductId);
            
            log.debug("IpoProductDao.findRowByPk()�����s���܂�");

            if(l_ipoProductRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            // �J�グ���I�̏ꍇ
            if (l_isAdvancedElection)
            {
	            Timestamp l_tsOed = l_ipoProductRow.getOfferEndDateProspec();
	            if (WEB3DateUtility.compareToDay(l_tsOed, l_tsCurrent) < 0)
	            {
	                log.exiting(STR_METHOD_NAME);
	                return null;
	            }
	            else
	            {
	                log.exiting(STR_METHOD_NAME);
	                return (IpoProductParams) l_ipoProductRow;
	            }
            }
            else    // ���I�̏ꍇ
            {
	            Timestamp l_tsOed = l_ipoProductRow.getOfferEndDatetime();
	            if (WEB3DateUtility.compareToSecond(l_tsOed, l_tsCurrent) <= 0)
	            {
	                log.exiting(STR_METHOD_NAME);
	                return null;
	            }
	            else
	            {
	                log.exiting(STR_METHOD_NAME);
	                return (IpoProductParams) l_ipoProductRow;
	            }
            }
            
        }
        catch (DataFindException l_ex)
        {
            log.debug("getIPO����Params is NULL");
            log.exiting(STR_METHOD_NAME);
            return null;
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
    }

    /**
     * (get���ϊ����ԋߌ��ʈꗗ)<BR>
     * �����̌ڋq�̕ێ����錈�ϊ����ԋ߂̌��ʂ��擾����B<BR>
     * <BR>
     * �P�j��������������&���������f�[�^�R���e�i<BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@����ID = �p�����[�^.�ڋq.getAccountId()�@@����<BR>
     * �@@�@@�⏕����ID = �⏕����.getSubAccountId()(*1)�@@����<BR>
     * �@@�@@���� <= to_date(?)�@@����<BR>
     * �@@�@@[��T�ԑO�̌��ʂ̏ꍇ]<BR>
     * �@@�@@�@@���� >= to_date(?)�@@���@@������ != 0<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@���� > to_date(?)�@@���@@������ != 0<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = "account_id = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "sub_account_id = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[�p�����[�^.is���ψ�T�ԑO == true�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "close_date >= to_date(?) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "close_date > to_date(?) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "quantity != ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B<BR>
     * �@@�@@��������ϊ����ăZ�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.getAccountId()<BR>
     * �@@�@@�@@�E�⏕����.getSubAccountId()<BR>
     * <BR>
     * �Q�j�p�����[�^.is���ψ�T�ԑO�ɂ��A�����̒l��<BR>
     * �@@�@@ArrayList�ɒǉ�����B<BR>
     * �@@�@@��������ϊ�(�t�H�[�}�b�g�FyyyyMMdd)���A�Z�b�g����B<BR>
     * <BR>
     * �@@�@@[�p�����[�^.is���ψ�T�ԑO == true�̏ꍇ]<BR>
     * �@@�@@�@@�E���ݎ���(*2) + 7��<BR>
     * �@@�@@�@@�E���ݎ���<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�E���ݎ��� + �ꃖ��<BR>
     * �@@�@@�@@�E���ݎ��� + 7��<BR>
     * <BR>
     * �R�jQueryProcessor.doFindAllQuery()�ɂāA���ϊԋߌ��ʂ��擾����B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@"�����e�[�u��(eqtype_contract)"<BR>
     * �@@arg1�F�@@�쐬������������������<BR>
     * �@@arg2�F�@@�쐬����ArrayList.toArray()<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �S�j�擾�����f�[�^��ԋp����B<BR>
     * <BR>
     * (*1)�⏕����<BR>
     * �@@�p�����[�^.�ڋq.getSubAccount()�ɂĎ擾�B<BR>
     * <BR>
     * �@@[getSubAccount()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�⏕�����^�C�v�F�@@SubAccountTypeEnum.�M�p�������<BR>
     * <BR>
     * (*2)���ݎ���<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * (������ԊǗ�.TIMESTAMP_TAG)�ɂĎ擾�������ݎ���<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_isSettleBeforeOneWeek - (is���ψ�T�ԑO)<BR>
     * ���ϊ�������T�ԑO���ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@���ψꃖ���O<BR>
     * true�F�@@���ψ�T�ԑO<BR>
     * @@return List
     * @@roseuid 414567D50221
     */
    public List getSettleContractList(WEB3GentradeMainAccount l_mainAccount, boolean l_isSettleBeforeOneWeek) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettleContractList(WEB3GentradeMainAccount, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }
                
        //�P�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQuery = new StringBuffer();
        
        List l_lisResultParams = null;
        try
        {
            List l_lisObjs = new ArrayList();
            //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
            //�P�|�P�j������������ɁA����������������쐬����B
            l_sbQuery.append(" account_id = ? and sub_account_id = ? and ");
            l_lisObjs.add(new Long(l_mainAccount.getAccountId()));
            
            long l_lngSubAccountId = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT).getSubAccountId();
            l_lisObjs.add(new Long(l_lngSubAccountId));
            
            //�Q�j�p�����[�^.is���ψ�T�ԑO�ɂ��A�����̒l��ArrayList�ɒǉ�����B
            //���ݎ���
            Date l_dCurrent = (Date) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            Date l_dCurrentBeforeOneWeek = WEB3DateUtility.addDay(l_dCurrent, 7);
            //[�p�����[�^.is���ψ�T�ԑO == true�̏ꍇ]
            if(l_isSettleBeforeOneWeek)
            {
                l_sbQuery.append("close_date <= to_date(?) and close_date >= to_date(?) and ");
                String l_strCurrentBeforeOneWeek = WEB3DateUtility.formatDate(l_dCurrentBeforeOneWeek, "yyyy/MM/dd");
                String l_strCurrent = WEB3DateUtility.formatDate(l_dCurrent, "yyyy/MM/dd");
                l_lisObjs.add(l_strCurrentBeforeOneWeek);
                l_lisObjs.add(l_strCurrent);
            }
            //[��L�ȊO�̏ꍇ]
            else
            {
                l_sbQuery.append("close_date <= add_months(to_date(?, 'yyyy/MM/dd'), 1) and close_date > to_date(?) and ");
                String l_strCurrent = WEB3DateUtility.formatDate(l_dCurrent, "yyyy/MM/dd");
                String l_strCurrentBeforeOneWeek = WEB3DateUtility.formatDate(l_dCurrentBeforeOneWeek, "yyyy/MM/dd");
                l_lisObjs.add(l_strCurrent);
                l_lisObjs.add(l_strCurrentBeforeOneWeek);
            }
            
            l_sbQuery.append("quantity != ? ");
            l_lisObjs.add(new Double(0));
            
            Object[] l_whereObjs = new Object[l_lisObjs.size()];
            
            l_lisObjs.toArray(l_whereObjs);
         
            //�R�jQueryProcessor.doFindAllQuery()�ɂāA���ϊԋߌ��ʂ��擾����B 
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = l_sbQuery.toString();
            
            log.debug("��������������&���������f�[�^�R���e�i: " + l_strWhere);
            
            l_lisResultParams = l_queryProcessor.doFindAllQuery(EqtypeContractRow.TYPE, l_strWhere, l_whereObjs);

            log.debug("QueryProcessor.doFindAllQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
        catch (NotFoundException l_ex) 
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }      
        
        log.exiting(STR_METHOD_NAME);
        if (l_lisResultParams != null && l_lisResultParams.size() == 0)
        {
            return null;
        }
        return l_lisResultParams;
    }

    /**
     * (get�؋����s���ꗗ)<BR>
     * �����̌ڋq�ɊY������f�[�^���؋����e�[�u������擾����B<BR>
     * <BR>
     * �P�j��������������&���������f�[�^�R���e�i<BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�ڋq.�،���ЃR�[�h�@@����<BR>
     * �@@�@@���X�R�[�h = �p�����[�^.�ڋq.���X�R�[�h�@@����<BR>
     * �@@�@@�ڋq�R�[�h = �p�����[�^.�ڋq.getAccountCode()�@@����<BR>
     * �@@�@@�폜�t���O = "0�Ffalse"<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = " institution_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "branch_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "account_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "delete_flag = ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.���X�R�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.getAccountCode()<BR>
     * �@@�@@�@@�E"0�Ffalse"<BR>
     * <BR>
     * �Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@"�؋����e�[�u��(ifo_deposit)"<BR>
     * �@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@arg2�F�@@�쐬����ArrayList.toArray()<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �R�j�Q�j�̌������ʂ�ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return List
     * @@roseuid 4145913501B4
     */
    public List getIfoDepositShortageList(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIfoDepositShortageList(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }
        
        //�P�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        //[��������] �P�|�P�j������������ɁA����������������쐬����B
        l_sbQuery.append(" institution_code = ? and ");
        l_sbQuery.append("branch_code = ? and ");
        l_sbQuery.append("account_code = ? and ");
        l_sbQuery.append("delete_flag = ? ");
        
        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
        l_lisQueryVars.add(l_mainAccount.getAccountCode());
        l_lisQueryVars.add(WEB3PvInfoDeleteFlagDef.DELETE_NO);
        
        //�Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        List l_lisResultParams = null; 
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("��������������: " + l_sbQuery.toString());
            log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());
            
            l_lisResultParams = l_queryProcessor.doFindAllQuery(
                IfoDepositRow.TYPE,
                l_sbQuery.toString(),
                null,
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doFindAllQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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

        if (l_lisResultParams.isEmpty())
        {
            log.debug("�؋����e�[�u���ɊY���f�[�^�Ȃ�");
            return null;
        }
        
        //�R�j�Q�j�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisResultParams;
    }

    /**
     * (get��������Params)<BR>
     * �����̖���ID�ɊY�����銔������Params��<BR>
     * ���������e�[�u�����擾����B<BR>
     * <BR>
     * �P�j���������e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@����ID = �p�����[�^.����ID<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�̌������ʂ�ԋp����B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams
     * @@roseuid 4147AB310115
     */
    public EqtypeProductParams getEqtypeProductParams(long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getEqtypeProductParams(long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("����ID: " + l_lngProductId);
        
        //�P�j���������e�[�u�����ȉ��̏����Ō�������B
        EqtypeProductRow l_epRow = null;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_epRow = (EqtypeProductRow) l_queryProcessor.doFindByPrimaryKeyQuery(new EqtypeProductPK(l_lngProductId));

            log.debug("QueryProcessor.doFindByPrimaryKeyQuery()�����s���܂�");
        }
        //null value return
        catch(DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            return null;
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
        
        //�Q�j�P�j�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        if (l_epRow == null)
        {
            return null;
        }
        return (EqtypeProductParams)l_epRow;
    }

    /**
     * (get�敨OP����Params)<BR>
     * �����̖���ID�ɊY������敨OP����Params��<BR>
     * �敨OP�����e�[�u�����擾����B<BR>
     * <BR>
     * �P�j�敨OP�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@����ID = �p�����[�^.����ID<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�̌������ʂ�ԋp����B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@return IfoProductParams
     * @@roseuid 41593981026F
     */
    public IfoProductParams getIfoProductParams(long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIfoProductParams(long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("����ID: " + l_lngProductId);
        
        //�P�j�敨OP�����e�[�u�����ȉ��̏����Ō�������B
        IfoProductRow l_ifoRow = null;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_ifoRow = (IfoProductRow) l_queryProcessor.doFindByPrimaryKeyQuery(new IfoProductPK(l_lngProductId));

            log.debug("QueryProcessor.doFindByPrimaryKeyQuery()�����s���܂�");
        }
        //null value return
        catch(DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            return null;
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
        
        //�Q�j�P�j�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        if (l_ifoRow == null)
        {
            return null;
        }
        return (IfoProductParams) l_ifoRow;
    }

    /**
     * (clone�{������Params)<BR>
     * �����̉{������Params���R�s�[���āA<BR>
     * �������e�̕ʃC���X�^���X���쐬���A�ԋp����B<BR>
     * @@param l_browseHistoryParams - (�{������Params)<BR>
     * �{������Params�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.data.BrowseHistoryParams
     * @@roseuid 4147C8E403AD
     */
    public BrowseHistoryParams cloneBrowseHistoryParams(BrowseHistoryParams l_browseHistoryParams)
    {
        final String STR_METHOD_NAME = " cloneBrowseHistoryParams(BrowseHistoryParams)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�{������Params���`�F�b�N����B
        if (l_browseHistoryParams == null)
        {
            log.error("�p�����[�^.�{������ParamsNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�{������ParamsNull�o���Ȃ��B");
        }

        //�������e�̕ʃC���X�^���X���쐬���A�ԋp����B
        BrowseHistoryParams l_bhParams = new BrowseHistoryParams();
        l_bhParams.setBrowseHistoryId(l_browseHistoryParams.getBrowseHistoryId());
        l_bhParams.setInstitutionCode(l_browseHistoryParams.getInstitutionCode());
        l_bhParams.setBranchCode(l_browseHistoryParams.getBranchCode());
        l_bhParams.setDisplayContentsId(l_browseHistoryParams.getDisplayContentsId());
        l_bhParams.setAccountCode(l_browseHistoryParams.getAccountCode());
//      *** ���捞No.016 START            
                 l_bhParams.setAccountId(l_browseHistoryParams.getAccountId());
//      *** ���捞No.016 END            
        l_bhParams.setReadFlag(l_browseHistoryParams.getReadFlag());
        l_bhParams.setDeleteFlag(l_browseHistoryParams.getDeleteFlag());
        l_bhParams.setLastReadTimestamp(l_browseHistoryParams.getLastReadTimestamp());
        l_bhParams.setCreatedTimestamp(l_browseHistoryParams.getCreatedTimestamp());
        l_bhParams.setLastUpdatedTimestamp(l_browseHistoryParams.getLastUpdatedTimestamp());

        log.exiting(STR_METHOD_NAME);
        return l_bhParams;
    }

    /**
     * (clone�\�����eParams)<BR>
     * �����̕\�����eParams���R�s�[���āA<BR>
     * �������e�̕ʃC���X�^���X���쐬���A�ԋp����B<BR>
     * @@param l_displayContentsParams - (�\�����eParams)<BR>
     * �\�����eParams�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.data.DisplayContentsParams
     * @@roseuid 4147C96201B9
     */
    public DisplayContentsParams cloneDisplayContentsParams(DisplayContentsParams l_displayContentsParams)
    {
        final String STR_METHOD_NAME = " cloneDisplayContentsParams(DisplayContentsParams)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�\�����eParams���`�F�b�N����B
        if (l_displayContentsParams == null)
        {
            log.error("�p�����[�^.�\�����eParamsNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�\�����eParamsNull�o���Ȃ��B");
        }

        //�������e�̕ʃC���X�^���X���쐬���A�ԋp����B
        DisplayContentsParams l_dcParams = new DisplayContentsParams();
        l_dcParams.setDisplayContentsId(l_displayContentsParams.getDisplayContentsId());
        l_dcParams.setInstitutionCode(l_displayContentsParams.getInstitutionCode());
        l_dcParams.setBranchCode(l_displayContentsParams.getBranchCode());
        l_dcParams.setConditionNo(l_displayContentsParams.getConditionNo());
        l_dcParams.setPriorityDiv(l_displayContentsParams.getPriorityDiv());
        l_dcParams.setTermFrom(l_displayContentsParams.getTermFrom());
        l_dcParams.setTermTo(l_displayContentsParams.getTermTo());
        l_dcParams.setDisplayTitle(l_displayContentsParams.getDisplayTitle());
        l_dcParams.setDisplayMessage(l_displayContentsParams.getDisplayMessage());
        l_dcParams.setDisplayColor(l_displayContentsParams.getDisplayColor());
        l_dcParams.setBlinkDisplayFlag(l_displayContentsParams.getBlinkDisplayFlag());
        l_dcParams.setDisplayUrl(l_displayContentsParams.getDisplayUrl());
        l_dcParams.setLastUpdateTimeDisplayFlag(l_displayContentsParams.getLastUpdateTimeDisplayFlag());
        l_dcParams.setEffectiveFlag(l_displayContentsParams.getEffectiveFlag());
        l_dcParams.setDisplayDevice(l_displayContentsParams.getDisplayDevice());
        l_dcParams.setLastUpdateMember(l_displayContentsParams.getLastUpdateMember());
        l_dcParams.setCreatedTimestamp(l_displayContentsParams.getCreatedTimestamp());
        l_dcParams.setLastUpdatedTimestamp(l_displayContentsParams.getLastUpdatedTimestamp());

        log.exiting(STR_METHOD_NAME);
        return l_dcParams;
    }

    /**
     * (create�\�����eParams)<BR>
     * �����̕\�����e��񂩂�<BR>
     * �\�����eParams���쐬���A�ԋp����B<BR>
     * <BR>
     * �P�jthis.get�\�����eParams()���R�[������B<BR>
     * <BR>
     * �@@[get�\�����eParams()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�\�����eID�F�@@�p�����[�^.�\�����e���.�\�����eID<BR>
     * <BR>
     * �@@null���ԋp���ꂽ�ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�jthis.clone�\�����eParams()���R�[�����A�\�����eParams(�N���[��)��<BR>
     * �@@�擾����B<BR>
     * <BR>
     * �@@[clone�\�����eParams()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�\�����eParams�F�@@�P�j�ɂĎ擾�����\�����eParams<BR>
     * <BR>
     * �R�j�Q�j�ɂĎ擾�����\�����eParams(�N���[��)�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�\�������ԍ�	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�D��敪	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\������From	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\������To	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\���^�C�g��	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\������	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\���F		= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�_�ŕ\���t���O	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@URL�w��		= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�ŏI�X�V�����\���t���O	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�L��/�����t���O	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�\���}��	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�ŏI�X�V��	= �p�����[�^.�\�����e���̓�������<BR>
     * �@@�X�V���t	= ���ݎ���(*1)<BR>
     * <BR>
     * �S�j�R�j�ɂăv���p�e�B�Z�b�g�����\�����eParams(�N���[��)��ԋp����B<BR>
     * <BR>
     * (*1)���ݎ���<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * (������ԊǗ�.TIMESTAMP_TAG)�ɂĎ擾�������ݎ���<BR>
     * @@param l_displayContentsUnit - (�\�����e���)<BR>
     * �\�����e���I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.data.DisplayContentsParams
     * @@roseuid 415D227A02CF
     */
    public DisplayContentsParams createDisplayContentsParams(WEB3PvInfoDisplayContentsUnit l_displayContentsUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createDisplayContentsParams(WEB3PvInfoDisplayContentsUnit)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�\�����e�����`�F�b�N����B
        if (l_displayContentsUnit == null)
        {
            log.error("�p�����[�^.�\�����e���Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�\�����e���Null�o���Ȃ��B");
        }

        //�P�jthis.get�\�����eParams()���R�[������B
        DisplayContentsParams l_dcParams = null;
        try
        {
            long l_lngDisplayContentsId = Long.parseLong(l_displayContentsUnit.displayContentsId);
            l_dcParams = getDisplayContentsParams(l_lngDisplayContentsId);

            log.debug("this.get�\�����eParams()�����s���܂�");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("getDisplayContentsParams error", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //null���ԋp���ꂽ�ꍇ�́Anull��ԋp����B
        if (l_dcParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�jthis.clone�\�����eParams()���R�[�����A�\�����eParams(�N���[��)���擾����B
        DisplayContentsParams l_dcParamsClone = cloneDisplayContentsParams(l_dcParams);


        //�R�j�Q�j�ɂĎ擾�����\�����eParams(�N���[��)�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //      (1)�\�������ԍ�    = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setConditionNo(l_displayContentsUnit.conditionNumber);
        //      (2)�D��敪    = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setPriorityDiv(l_displayContentsUnit.priorityDiv);
        //      (3)�\������From  = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setTermFrom(WEB3DateUtility.getDate(l_displayContentsUnit.listStartDate, "yyyyMMddHHmm"));
        //      (4)�\������To  = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setTermTo(WEB3DateUtility.getDate(l_displayContentsUnit.listEndDate, "yyyyMMddHHmm"));
        //      (5)�\���^�C�g��   = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setDisplayTitle(l_displayContentsUnit.displayTitle);
        //      (6)�\������ = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setDisplayMessage(l_displayContentsUnit.displayMessage);
        //      (7)�\���F      = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setDisplayColor(l_displayContentsUnit.displayColor);        
        // �@@   (8)�_�ŕ\���t���O  = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setBlinkDisplayFlag(l_displayContentsUnit.blinkDisplayFlag ?
            WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES :
            WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_NO);
        //      (9) �@@URL�w��        = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setDisplayUrl(l_displayContentsUnit.displayUrl);
        // �@@   (10)�ŏI�X�V�����\���t���O  = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setLastUpdateTimeDisplayFlag(l_displayContentsUnit.lastUpdateTimeDisplayFlag ?
            WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_NO :
            WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES);
        // �@@   (11)�L��/�����t���O = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setEffectiveFlag(l_displayContentsUnit.effectiveFlag ?
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_NO :
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES);
        //�@@    (12)�\���}�� = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setDisplayDevice(l_displayContentsUnit.displayDevice);
        // �@@   (13)�ŏI�X�V��    = �p�����[�^.�\�����e���̓�������
        l_dcParamsClone.setLastUpdateMember(l_displayContentsUnit.lastUpdateMember);
        // �@@   (14)�X�V���t = ���ݎ���(*1)<BR>
        l_dcParamsClone.setLastUpdatedTimestamp((Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        //�S�j�R�j�ɂăv���p�e�B�Z�b�g�����\�����eParams(�N���[��)��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dcParamsClone;
    }

    /**
     * (is���Y�ۗL)<BR>
     * �����̖����^�C�v�ɊY�����鎑�Y��ۗL���Ă��邩���ʂ���B<BR>
     * <BR>
     * �ۗL���Ă���ꍇtrue�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �P�j��������������&���������f�[�^�R���e�i<BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@����ID = �p�����[�^.�ڋq.����ID�@@����<BR>
     * �@@�@@�����^�C�v = �p�����[�^.�����^�C�v�@@����<BR>
     * �@@�@@���� != 0�@@����<BR>
     * �@@�@@�~�j���敪 = (�p�����[�^.is�~�j���̒l�ɂ��)<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = " account_id = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and product_type = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and quantity != ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and mini_stock_div = ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���Z�b�g����B<BR>
     * �@@�@@��������ϊ����ăZ�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.getAccountId()<BR>
     * �@@�@@�@@�E�p�����[�^.�����^�C�v<BR>
     * �@@�@@�@@�E0<BR>
     * <BR>
     * �Q�j�p�����[�^.is�~�j���ɂ��A�ȉ��̏�����ArrayList�ɒǉ�����B<BR>
     * �@@[�p�����[�^.is�~�j�� == true�̏ꍇ]<BR>
     * �@@�@@�@@��������ArrayList.add("1�F�~�j��")<BR>
     * <BR>
     * �@@[�p�����[�^.is�~�j�� == false�̏ꍇ]<BR>
     * �@@�@@�@@��������ArrayList.add("0�FDEFAULT")<BR>
     * <BR>
     * �R�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�h�ۗL���Y�e�[�u��(asset)�h<BR>
     * �@@arg1�F�@@�쐬������������������<BR>
     * �@@arg2�F�@@�쐬����ArrayList.toArray()<BR>
     * <BR>
     * �@@�������ʂ��擾�ł����ꍇ��true�A�ȊOfalse��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * (ProductTypeEnum�ɂĒ�`)<BR>
     * @@param l_isMiniStock - (is�~�j��)<BR>
     * �擾�ΏەۗL���Y���A�~�j�����ǂ����̃t���O<BR>
     * <BR>
     * true�F�@@�~�j��<BR>
     * false�F�@@�~�j���łȂ�<BR>
     * @@return boolean
     * @@roseuid 41590F7E0221
     */
    public boolean isAssetHas(
        WEB3GentradeMainAccount l_mainAccount,
        ProductTypeEnum l_productType,
        boolean l_isMiniStock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isAssetHas(WEB3GentradeMainAccount, ProductTypeEnum, boolean)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }

        //�p�����[�^.�����^�C�v���`�F�b�N����B
        if (l_productType == null)
        {
            log.error("�p�����[�^.�����^�C�vNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�����^�C�vNull�o���Ȃ��B");
        }

        log.debug("�����^�C�v: " + l_productType);
        log.debug("is�~�j��: " + l_isMiniStock);

        //�P�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();

        //�P�|�P�j������������ɁA����������������쐬����B<BR>
        l_sbQuery.append(" account_id = ? ");
        l_sbQuery.append("and product_type = ? ");
        l_sbQuery.append("and quantity != ? ");
        l_sbQuery.append("and mini_stock_div = ? ");
        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        l_lisQueryVars.add(new Long(l_mainAccount.getAccountId()));
        l_lisQueryVars.add(new Integer(l_productType.intValue()));
        l_lisQueryVars.add(new Long(0));
        //�Q�j�p�����[�^.is�~�j���ɂ��A�ȉ��̏�����ArrayList�ɒǉ�����B
        if (l_isMiniStock)
        {
            l_lisQueryVars.add(WEB3MiniStockDivDef.MINI_STOCK);
        }
        else
        {
            l_lisQueryVars.add(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        }

        //�R�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("��������������: " + l_sbQuery.toString());
            log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());
            
            List l_lisResultRow = l_queryProcessor.doFindAllQuery(
                AssetRow.TYPE,
                l_sbQuery.toString(),
                null,
                l_lisQueryVars.toArray());
            
            log.debug("QueryProcessor.doFindAllQuery()�����s���܂�");

            //�������ʂ��擾�ł����ꍇ��true�A�ȊOfalse��ԋp����B
            if(l_lisResultRow != null && l_lisResultRow.size() > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }              
        }
        catch (DataFindException l_ex)
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
    }

    /**
     * (is���ʕۗL)<BR>
     * �����̖����^�C�v�A�敨�^�I�v�V�����敪�ɊY�����錚�ʂ�<BR>
     * �ۗL���Ă��邩���ʂ���B<BR>
     * <BR>
     * �ۗL���Ă���ꍇtrue�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.�����^�C�v�ɂ�茟���ΏۂƂȂ�e�[�u�������肷��B<BR>
     * <BR>
     * �@@[�p�����[�^.�����^�C�v == ProductTypeEnum.�����̏ꍇ]<BR>
     * �@@�@@�����Ώۃe�[�u�� = �����e�[�u��(eqtype_contract)<BR>
     * <BR>
     * �@@[�p�����[�^.�����^�C�v == ProductTypeEnum.�敨�I�v�V�����̏ꍇ]<BR>
     * �@@�@@�����Ώۃe�[�u�� = ���ʃe�[�u��(ifo_contract)<BR>
     * <BR>
     * �Q�j��������������&���������f�[�^�R���e�i<BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@����ID = �p�����[�^.�ڋq.����ID�@@����<BR>
     * �@@�@@���ʐ� != 0�@@����<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = " account_id = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and quantity != ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���Z�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.getAccountId()�@@��������ϊ����ăZ�b�g�B<BR>
     * �@@�@@�@@�E"0"<BR>
     * �@@<BR>
     * �R�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�P�j�ɂČ��肵�������Ώۃe�[�u��<BR>
     * �@@arg1�F�@@�쐬������������������<BR>
     * �@@arg2�F�@@�쐬����ArrayList.toArray()<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ��false��ԋp����B<BR>
     * <BR>
     * �S�j�p�����[�^.�����^�C�v == ProductTypeEnum.�����̏ꍇ�A<BR>
     * �@@�������ʂ��擾�ł����ꍇ��true��ԋp����B<BR>
     * <BR>
     * �T�j�p�����[�^.�����^�C�v == ProductTypeEnum.�敨�I�v�V�����̏ꍇ�A<BR>
     * �@@�R�j�ɂĎ擾�����S�Ă̌���Params�ɑ΂��A�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�T�|�P�jthis.get�敨OP����Params()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[get�敨OP����Params()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@����ID�F�@@�擾��������Params.����ID<BR>
     * <BR>
     * �@@�T�|�Q�jget�敨OP����Params()�̖߂�l.�敨�^�I�v�V�����敪 == <BR>
     * �@@�@@�@@�@@�@@�@@�p�����[�^.�敨�^�I�v�V�����敪�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �U�jfalse��ԋp����B�@@���w�肵�����ʂ�ۗL���Ă��Ȃ��ׁB<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * (ProductTypeEnum�ɂĒ�`)<BR>
     * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪)<BR>
     * �敨�^�I�v�V�����敪<BR>
     * <BR>
     * 0�F�@@DEFAULT<BR>
     * 1�F�@@�敨<BR>
     * 2�F�@@�I�v�V����<BR>
     * @@return boolean
     * @@roseuid 4159148800B9
     */
    public boolean isContractHas(
        WEB3GentradeMainAccount l_mainAccount,
        ProductTypeEnum l_productType,
        String l_strFutureOptionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isContractHas(WEB3GentradeMainAccount, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }

        //�p�����[�^.�����^�C�v���`�F�b�N����B
        if (l_productType == null)
        {
            log.error("�p�����[�^.�����^�C�vNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�����^�C�vNull�o���Ȃ��B");
        }
        
        if (l_productType.intValue() != ProductTypeEnum.IntValues.EQUITY && 
            l_productType.intValue() != ProductTypeEnum.IntValues.IFO)
        {
            log.error("�p�����[�^.�����^�C�v�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�����^�C�v�l�s���B");
        }

        if(l_strFutureOptionDiv != null && 
            !WEB3FuturesOptionDivDef.DEFAULT.equals(l_strFutureOptionDiv) &&
            !WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv) &&
            !WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv))
        {
            log.error("�p�����[�^.�敨�^�I�v�V�����敪�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�敨�^�I�v�V�����敪�l�s���B");
        }

        //�P�j�p�����[�^.�����^�C�v�ɂ�茟���ΏۂƂȂ�e�[�u�������肷��B
        RowType l_rowType = (l_productType.intValue() == ProductTypeEnum.IntValues.EQUITY) 
            ? EqtypeContractRow.TYPE //[�p�����[�^.�����^�C�v == ProductTypeEnum.�����̏ꍇ]
            : IfoContractRow.TYPE;   //[�p�����[�^.�����^�C�v == ProductTypeEnum.�敨�I�v�V�����̏ꍇ] 

        //�Q�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();

        //�P�|�P�j������������ɁA����������������쐬����B
        l_sbQuery.append(" account_id = ? ");
        l_sbQuery.append("and quantity != ? ");

        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        l_lisQueryVars.add(new Long(l_mainAccount.getAccountId()));
        l_lisQueryVars.add("0");
        
        List l_lisResultRow = null;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();

            log.debug("��������������: " + l_sbQuery.toString());
            log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());

            //�R�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            l_lisResultRow = l_queryProcessor.doFindAllQuery(
                l_rowType,
                l_sbQuery.toString(),
                null,
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doFindAllQuery()�����s���܂�");
        }
        catch (DataFindException l_ex)
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
                
        //�������ʂ��擾�ł��Ȃ������ꍇ��false��ԋp����B
        if(l_lisResultRow == null || l_lisResultRow != null && l_lisResultRow.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�S�j�p�����[�^.�����^�C�v == ProductTypeEnum.�����̏ꍇ�A�������ʂ��擾�ł����ꍇ��true��ԋp����B
        if(l_productType.intValue() == ProductTypeEnum.IntValues.EQUITY)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�T�j�p�����[�^.�����^�C�v == ProductTypeEnum.�敨�I�v�V�����̏ꍇ�A�R�j�ɂĎ擾�����S�Ă̌���Params�ɑ΂��A�ȉ��̏������J��Ԃ��B
        else if (l_productType.intValue() == ProductTypeEnum.IntValues.IFO)
        {
            int l_intTemp = l_lisResultRow.size();
            for(int i = 0; i < l_intTemp; i++)
            {
                //�T�|�P�jthis.get�敨OP����Params()���R�[������B
                IfoContractParams l_ifoContractParams = (IfoContractParams)l_lisResultRow.get(i);
                //[get�敨OP����Params()�ɃZ�b�g����p�����[�^]
                IfoProductParams l_ifoProductParams = getIfoProductParams(l_ifoContractParams.product_id);
                //�T�|�Q�jget�敨OP����Params()�̖߂�l.�敨�^�I�v�V�����敪 == �p�����[�^.�敨�^�I�v�V�����敪�̏ꍇ�Atrue��ԋp����B
                if (l_ifoProductParams.future_option_div.equals(l_strFutureOptionDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
        }

        //�U�jfalse��ԋp����B�@@���w�肵�����ʂ�ۗL���Ă��Ȃ��ׁB
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (get���Y�]�͏��)<BR>
     * �����̕⏕�����ɊY�����鎑�Y�]�͏���ԋp����B<BR>
     * �߂�l�F�@@���Y�]�͏��<�����ڋq> or<BR>
     *              ���Y�]�͏��<�M�p�ڋq><BR>
     * ���{���\�b�h���g�p����ꍇ�́A�g�p����T�[�r�X�̃T�[�r�X�C���^�Z�v�^.onReturn()�AonThrowable()<BR>
     * �@@���ɂāA"TRADING_POWER_INFO"�̐ݒ�L�[�ɂĐݒ肳��Ă���f�[�^���N���A���邱�ƁB<BR>
     * �P�jThreadLocalSystemAttributesRegistry.getAttribute()���\�b�h���R�[������B<BR>
     * �@@[getAttribute()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@"TRADING_POWER_INFO"<BR>
     * <BR>
     * �Q�j�P�j�̖߂�l != null�̏ꍇ�A�P�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * �R�j�Q�j�ȊO�̏ꍇ�A�ȉ��̏��������{����B<BR>
     * �@@�R�|�P�j�p�����[�^.�⏕����.getMainAccount()���\�b�h�ɂ�<BR>
     * �@@�@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�R�|�Q�j�ڋq�I�u�W�F�N�g.is�M�p�����J��()���\�b�h���R�[������B<BR>
     * �@@�R�|�R�j���Y�]�͏��N���X�̎擾����<BR>
     * �@@�@@�R�|�Q�j�̃��\�b�h�̖߂�l���A<BR>
     * �@@�@@[false�̏ꍇ]<BR>
     * �@@�@@�@@����]�̓T�[�r�X.get���Y�]�͏��<�����ڋq>()���\�b�h���R�[������B<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@����]�̓T�[�r�X.get���Y�]�͏��<�M�p�ڋq>()���\�b�h���R�[������B<BR>
     * �@@�@@���eget���Y�]�͏��<...�ڋq>()���\�b�h�����ɂ́A�p�����[�^.�⏕�������Z�b�g���邱�ƁB<BR>
     * �@@�R�|�S�jThreadLocalSystemAttributesRegistry.getAttribute()���\�b�h���R�[�����A<BR>
     * �@@�@@�R�|�R�j�̖߂�l���Z�b�g����B<BR>
     * �@@�@@[setAttribute()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@"TRADING_POWER_INFO"<BR>
     * �@@�R�|�T�j�R�|�R�j�̖߂�l��ԋp����B<BR>
     * @@param l_subAccount<BR>
     * @@return<BR>
     */
    public Object getTradingPowerInfo(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradingPowerInfo(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.error("�p�����[�^.Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.Null�o���Ȃ��B");
        }
        //�P�jThreadLocalSystemAttributesRegistry.getAttribute()���\�b�h���R�[������B
        Object l_objPowerInfo = ThreadLocalSystemAttributesRegistry.getAttribute(WEB3PvInfoTradingPowerInfoDef.TRADING_POWER_INFO);
        // �Q�j�P�j�̖߂�l != null�̏ꍇ�A�P�j�̖߂�l��ԋp����B
        if (l_objPowerInfo != null)
        {
            return l_objPowerInfo;
        }
        //�R�j�Q�j�ȊO�̏ꍇ�A�ȉ��̏��������{����B
        //�R�|�P�j�p�����[�^.�⏕����.getMainAccount()���\�b�h�ɂČڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�R�|�Q�j�ڋq�I�u�W�F�N�g.is�M�p�����J��()���\�b�h���R�[������B
        boolean l_blnIsMarginAccount = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        //�R�|�R�j���Y�]�͏��N���X�̎擾����
        //�R�|�Q�j�̃��\�b�h�̖߂�l���A[false�̏ꍇ]
        WEB3TPTradingPowerService l_service = 
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        if (!l_blnIsMarginAccount)
        {
            //����]�̓T�[�r�X.get���Y�]�͏��<�����ڋq>()���\�b�h���R�[������B
            l_objPowerInfo = l_service.getTradingPowerCalcEquity(l_subAccount);
        }        
        //[��L�ȊO�̏ꍇ]
        else
        {
            l_objPowerInfo = l_service.getTradingPowerCalcMargin(l_subAccount);
        }
        //�R�|�S�jThreadLocalSystemAttributesRegistry.getAttribute()���\�b�h���R�[�����A
        //�R�|�R�j�̖߂�l���Z�b�g����B[setAttribute()�ɃZ�b�g����p�����[�^]
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3PvInfoTradingPowerInfoDef.TRADING_POWER_INFO,l_objPowerInfo);
        //�R�|�T�j�R�|�R�j�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_objPowerInfo;
    }
    
    /**
     * (get��������������)<BR>
     * ����������������ԋp����B<BR>
     * �P�jthis.get���Y�]�͏��()���\�b�h���R�[������B<BR>
     * �@@[get���Y�]�͏��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * <BR>
     * �Q�j�p�����[�^.�⏕����.getMainAccount()���\�b�h�ɂ�<BR>
     * �@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�ڋq�I�u�W�F�N�g.is�M�p�����J��()���\�b�h���R�[������B<BR>
     * <BR>
     * �S�j�p�����[�^.is�M�p�����J�� == false(�����ڋq)�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * �@@�S�|�P�j�P�j�̖߂�l�����Y�]�͏��<�����ڋq>�^�ɃL���X�g����B<BR>
     * �@@�S�|�Q�j�ȉ��̏������A�C���f�b�N�X�͈̔�(0�`5)�ɂ��ČJ��Ԃ��B<BR>
     * �@@�@@�S�|�Q�|�P�j���Y�]�͏��<�����ڋq>.calc���������z()���R�[������B<BR>
     * �@@�@@�@@[calc���������z()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�w����F�@@���݂̃C���f�b�N�X<BR>
     * �@@�@@�S�|�Q�|�Q�j�S�|�Q�|�P�j�̌v�Z���ʂ�0���傫���ꍇ(0<�v�Z����)�A <BR>
     * �@@�@@�@@���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h��<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
     * �@@�@@�@@[get�c�Ɠ�()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�w����F�@@���݂̃C���f�b�N�X�@@�@@<BR>
     * <BR>
     * �T�j�S�j�ȊO�̏ꍇ�A�ȉ��̏��������{����B<BR>
     * �@@�T�|�P�j�P�j�̖߂�l�����Y�]�͏��<�M�p�ڋq>�^�ɃL���X�g����B<BR>
     * �@@�T�|�Q�j�ȉ��̏������A�C���f�b�N�X�͈̔�(0�`5)�ɂ��ČJ��Ԃ��B<BR>
     * �@@�@@�T�|�Q�|�P�j���Y�]�͏��<�M�p�ڋq>.calc���������z()���R�[������B<BR>
     * �@@�@@�@@[calc���������z()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�w����F�@@���݂̃C���f�b�N�X<BR>
     * �@@�@@�T�|�Q�|�Q�j�T�|�Q�|�P�j�̌v�Z���ʂ�0���傫���ꍇ(0<�v�Z����)�A<BR>
     * �@@�@@�@@���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h��<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
     * �@@�@@�@@[get�c�Ɠ�()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�w����F�@@���݂̃C���f�b�N�X�@@<BR>
     * <BR>�@@
     * �S�jnull��ԋp����B�@@�������������������Ă��Ȃ��ׁB<BR>
     * @@param l_subAccount<BR>
     * @@return Date<BR>
     */
    public Date getPayClaimGenDate(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPayClaimGenDate(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.error("�p�����[�^.Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.Null�o���Ȃ��B");
        }
        
        //�P�jthis.get���Y�]�͏��()���\�b�h���R�[������B
        Object l_objPowerInfo = this.getTradingPowerInfo(l_subAccount);
        //�Q�j�p�����[�^.�⏕����.getMainAccount()���\�b�h�ɂČڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�R�j�ڋq�I�u�W�F�N�g.is�M�p�����J��()���\�b�h���R�[������B�R�j�ڋq�I�u�W�F�N�g.is�M�p�����J��()���\�b�h���R�[������B       
        boolean l_blnIsMarginAccount = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        if (!l_blnIsMarginAccount)
        {
            //�@@�S�|�P�j�P�j�̖߂�l�����Y�]�͏��<�����ڋq>�^�ɃL���X�g����B
            WEB3TPTradingPowerCalcEquity l_powerCalcEquity = (WEB3TPTradingPowerCalcEquity)l_objPowerInfo;
            //�S�|�Q�j�ȉ��̏������A�C���f�b�N�X�͈̔�(0�`5)�ɂ��ČJ��Ԃ��B
            for (int i = 0; i < 6; i++)
            {
            	/*
                //�S�|�Q�|�P�j���Y�]�͏��<�����ڋq>.calc���o�\����()���R�[������B
                double l_dblBalance = l_powerCalcEquity.calcActualPaymentBalance(i);
                //�S�|�Q�|�Q�j�S�|�Q�|�P�j�̌v�Z���ʂ��}�C�i�X(<0)�������ꍇ�A
                if (l_dblBalance < 0)
                {
                    //���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h�̖߂�l��ԋp����B
                    log.exiting(STR_METHOD_NAME);
                    return l_powerCalcEquity.getCalcCondition().getBizDate(i);
                }
                */
				//2006/03/13 �C��	
				//�S�|�Q�|�P�j���Y�]�͏��<�����ڋq>.calc���������z()���R�[������B
				double l_dblBalance = l_powerCalcEquity.calcDemandamount(i);
				//�S�|�Q�|�Q�j�S�|�Q�|�P�j�̌v�Z���ʂ�0���傫���ꍇ(0<�v�Z����)�A 
				if (l_dblBalance > 0)
				{
					//���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h�̖߂�l��ԋp����B
					log.exiting(STR_METHOD_NAME);
					return l_powerCalcEquity.getCalcCondition().getBizDate(i);
				}
                
            }

        }
        //�T�j�S�j�ȊO�̏ꍇ�A�ȉ��̏��������{����B
        else
        {
            //�T�|�P�j�P�j�̖߂�l�����Y�]�͏��<�M�p�ڋq>�^�ɃL���X�g����B
            WEB3TPTradingPowerCalcMargin l_powerCalcMargin = (WEB3TPTradingPowerCalcMargin) l_objPowerInfo;
            //�T�|�Q�j�ȉ��̏������A�C���f�b�N�X�͈̔�(0�`5)�ɂ��ČJ��Ԃ��B
            for (int i = 0; i < 6; i++)
            {
            	/*
                //�T�|�Q�|�P�j���Y�]�͏��<�M�p�ڋq>.calc���o�\����()���R�[������B
                double l_dblBalance = l_powerCalcMargin.calcActualPaymentBalance(i);
                //�T�|�Q�|�Q�j���Y�]�͏��<�M�p�ڋq>.calc�Ǐؗ]��()���R�[������B
                double l_dblCallPower = l_powerCalcMargin.calcMarginCallPower(i);
                
                //�T�|�Q�|�R�j�T�|�Q�|�P�j�A�T�|�Q�|�Q�j�̌v�Z���ʂ��}�C�i�X(<0)�������ꍇ�A
                if (l_dblBalance < 0 || l_dblCallPower < 0)
                {
                    //���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h�̖߂�l��ԋp����B
                    log.exiting(STR_METHOD_NAME);
                    return l_powerCalcMargin.getCalcCondition().getBizDate(i);
                }
                */
				//�T�|�Q�|�P�j���Y�]�͏��<�M�p�ڋq>.calc���������z()���R�[������B
				double l_dblBalance = l_powerCalcMargin.calcDemandamount(i);
				//�T�|�Q�|�Q�j���Y�]�͏��<�M�p�ڋq>.calc�Ǐؗ]��()���R�[������B
				//double l_dblCallPower = l_powerCalcMargin.calcMarginCallPower(i);
                
				//�T�|�Q�|�Q�j�T�|�Q�|�P�j�̌v�Z���ʂ�0���傫���ꍇ(0<�v�Z����)�A 
				if (l_dblBalance > 0)
				{
					//���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h�̖߂�l��ԋp����B
					log.exiting(STR_METHOD_NAME);
					return l_powerCalcMargin.getCalcCondition().getBizDate(i);
				}
                
            }
        }
        //�S�jnull��ԋp����B�@@�������������������Ă��Ȃ��ׁB
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get���֋�������)<BR>
     * ���֋���������ԋp����B<BR>
     * �P�jthis.get���Y�]�͏��()���\�b�h���R�[������B<BR>
     * �@@[get���Y�]�͏��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * <BR>
     * �Q�j�p�����[�^.�⏕����.getMainAccount()���\�b�h�ɂ�<BR>
     * �@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�ڋq�I�u�W�F�N�g.is�M�p�����J��()���\�b�h���R�[������B<BR>
     * <BR>
     * �S�j�p�����[�^.is�M�p�����J�� == false(�����ڋq)�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B�@@��true�̏ꍇ�Anull��ԋp���ďI������B<BR>
     * �@@�S�|�P�j�P�j�̖߂�l�����Y�]�͏��<�����ڋq>�^�ɃL���X�g����B<BR>
     * �@@�S�|�Q�j�a��،��]�����ڋq�̏ꍇ�A<BR>
     * �@@�@@(���Y�]�͏��<�����ڋq>.�]�͌v�Z����.�a��،��]�����敪 == true�@@����<BR>
     * �@@�@@���Y�]�͏��<�����ڋq>.�]�͌v�Z����.�a��،��]����<���������]��> == true)<BR>
     * �@@�@@�ȉ��̏��������{����B<BR>
     * �@@�@@�S�|�Q�|�P�j������ԊǗ�.is�s��J�ǎ��ԑ�()���\�b�h���R�[������B<BR>
     * �@@�@@�S�|�Q�|�Q�j�S�|�Q�|�P�j�̖߂�l == false�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�w��� = 1�@@�Ƃ���B<BR>
     * �@@�@@�@@�@@�ȊO�A�w��� = 0�@@�Ƃ���B<BR>
     * �@@�@@�S�|�Q�|�R�j���Y�]�͏��<�����ڋq>.calc���֋�()���R�[������B<BR>
     * �@@�@@�@@�@@[calc���֋�()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�w����F�@@���肵���w���<BR>
     * �@@�@@�S�|�Q�|�S�j�S�|�Q�|�R�j�̌v�Z���ʂ�0���傫���ꍇ(0<�v�Z����)�A����<BR>
     * �@@�@@�@@�@@���Y�]�͏��<�����ڋq>.�]�͌v�Z����.�����~�敪 == "����\"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h��<BR>
     * �@@�@@�@@�@@�߂�l��ԋp����B<BR>
     * �@@�@@�@@�@@[get�c�Ɠ�()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�w����F�@@���肵���w���<BR>
     * �@@�S�|�R�j�S�|�Q�j�ȊO�̏ꍇ�A(�a��،��]�����ڋq�łȂ�)<BR>
     * �@@�@@�ȉ��̏������A�C���f�b�N�X�͈̔�(0�`5)�ɂ��ČJ��Ԃ��B<BR>
     * �@@�@@�S�|�R�|�P�j���Y�]�͏��<�����ڋq>.calc���֋�()���R�[������B<BR>
     * �@@�@@�@@[calc���֋�()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�w����F�@@���݂̃C���f�b�N�X<BR>
     * �@@�@@�S�|�R�|�Q�j�S�|�R�|�P�j�̌v�Z���ʂ�0���傫���ꍇ(0<�v�Z����)�A����<BR>
     * �@@�@@�@@���Y�]�͏��<�����ڋq>.�]�͌v�Z����.�����~�敪 == "����\"�̏ꍇ�A<BR>
     * �@@�@@�@@���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h��<BR>
     * �@@�@@�@@�߂�l��ԋp����B<BR>
     * �@@�@@�@@[get�c�Ɠ�()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�w����F�@@���݂̃C���f�b�N�X�@@�@@<BR>
     * <BR>
     *�T�j�S�j�ȊO�̏ꍇ�A�ȉ��̏��������{����B<BR>
     *�@@�T�|�P�j�P�j�̖߂�l�����Y�]�͏��<�M�p�ڋq>�^�ɃL���X�g����B<BR>
     *�@@�T�|�Q�j�ȉ��̏������A�C���f�b�N�X�͈̔�(0�`5)�ɂ��ČJ��Ԃ��B<BR>
     *�@@�@@�T�|�Q�|�P�j���Y�]�͏��<�M�p�ڋq>.calc���֋�()���R�[������B<BR>
     *<BR>
     *�@@�@@�@@[calc���֋�()�ɃZ�b�g����p�����[�^]<BR>
     *�@@�@@�@@�@@�w����F�@@���݂̃C���f�b�N�X<BR>
     *<BR>
     *�@@�@@�T�|�Q�|�Q�j�T�|�Q�|�P�j�̌v�Z���ʂ�0���傫���ꍇ(0<�v�Z����)�A����<BR>
     *�@@�@@�@@���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.is�����~�敪() == false(����\)�̏ꍇ�A<BR>
     *�@@�@@�@@���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h��<BR>
     *�@@�@@�@@�߂�l��ԋp����B<BR>
     *<BR>
     *�@@�@@�@@[get�c�Ɠ�()�ɃZ�b�g����p�����[�^]<BR>
     *�@@�@@�@@�@@�w����F�@@���݂̃C���f�b�N�X�@@�@@<BR>
     *<BR>
     *�U�jnull��ԋp����B�@@�����֋����������Ă��Ȃ��ׁB<BR>
     * @@param l_subAccount<BR>
     * @@return<BR>
     */
    public Date getAdvanceGenDate(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAdvanceGenDate(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.error("�p�����[�^.Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.Null�o���Ȃ��B");
        }
        //�P�jthis.get���Y�]�͏��()���\�b�h���R�[������B
        Object l_objPowerInfo = this.getTradingPowerInfo(l_subAccount);
        //�Q�j�p�����[�^.�⏕����.getMainAccount()���\�b�h�ɂČڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�R�j�ڋq�I�u�W�F�N�g.is�M�p�����J��()���\�b�h���R�[������B�R�j�ڋq�I�u�W�F�N�g.is�M�p�����J��()���\�b�h���R�[������B       
        boolean l_blnIsMarginAccount = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        // �S�j�p�����[�^.is�M�p�����J�� == false(�����ڋq)�̏ꍇ�A
        //�ȉ��̏��������{����B�@@
        if (!l_blnIsMarginAccount)
        {
            //�@@�S�|�P�j�P�j�̖߂�l�����Y�]�͏��<�����ڋq>�^�ɃL���X�g����B
            WEB3TPTradingPowerCalcEquity l_powerCalcEquity = (WEB3TPTradingPowerCalcEquity)l_objPowerInfo;
            //�S�|�Q�j�a��،��]�����ڋq�̏ꍇ�A
            //���Y�]�͏��<�����ڋq>.�]�͌v�Z����.�a��،��]�����敪 == true�@@����
            //���Y�]�͏��<�����ڋq>.�]�͌v�Z����.�a��،��]����<���������]��> == true)
            if (l_powerCalcEquity.getCalcCondition().isAssetEvalDiv() 
                    && l_powerCalcEquity.getCalcCondition().isEquityEvalDiv())
            {
                //�S�|�Q�|�P�j������ԊǗ�.is�s��J�ǎ��ԑ�()���\�b�h���R�[������B
                int l_intDate;
                if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
                {
                    //�S�|�Q�|�Q�j�S�|�Q�|�P�j�̖߂�l == false�̏ꍇ�A�w��� = 1�@@�Ƃ���B
                    l_intDate = 1;
                }
                //�ȊO�A�w��� = 0�@@�Ƃ���B
                else
                {
                    l_intDate = 0;
                }
                /*
                //�S�|�Q�|�R�j���Y�]�͏��<�����ڋq>.calc�g�p�\����()���R�[������B 
                double l_dblBalance = l_powerCalcEquity.calcActualAccountBalance(l_intDate); 
                //�S�|�Q�|�S�j�S�|�Q�|�R�j�̌v�Z���ʂ��}�C�i�X(<0)�������ꍇ�@@
                //�����Y�]�͏��<�����ڋq>.�]�͌v�Z����.�����~�敪 == "����\"�̏ꍇ�A
                if (l_dblBalance < 0 && !l_powerCalcEquity.getCalcCondition().isTradingStop())
                {
                    //���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h�̖߂�l��ԋp����B
                    log.exiting(STR_METHOD_NAME);
                    return l_powerCalcEquity.getCalcCondition().getBizDate(l_intDate);
                }
                */
				//�S�|�Q�|�R�j���Y�]�͏��<�����ڋq>.calc���֋�()���R�[������B 
				double l_dblBalance = l_powerCalcEquity.calcDebitAmount(l_intDate); 
				//�S�|�Q�|�S�j�S�|�Q�|�R�j�̌v�Z���ʂ�0���傫���ꍇ(0<�v�Z����)�A�@@
				//�����Y�]�͏��<�����ڋq>.�]�͌v�Z����.�����~�敪 == "����\"�̏ꍇ�A
				if (l_dblBalance > 0 && !l_powerCalcEquity.getCalcCondition().isTradingStop())
				{
					//���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h�̖߂�l��ԋp����B
					log.exiting(STR_METHOD_NAME);
					return l_powerCalcEquity.getCalcCondition().getBizDate(l_intDate);
				}
                
            }
            //�S�|�R�j�S�|�Q�j�ȊO�̏ꍇ�A(�a��،��]�����ڋq�łȂ�)    
            else
            {
                //�ȉ��̏������A�C���f�b�N�X�͈̔�(0�`5)�ɂ��ČJ��Ԃ��B
                for (int i = 0; i <= WEB3PvInfoTPBizDateCheckNumDef.MAX_DAYS; i++)
                {
                	/*
                    //�S�|�R�|�P�j���Y�]�͏��<�����ڋq>.calc�g�p�\����()���R�[������B
                    double l_dblBalance = l_powerCalcEquity.calcActualPaymentBalance(i);
                    //�S�|�R�|�Q�j�S�|�R�|�P�j�̌v�Z���ʂ��}�C�i�X(<0)�������ꍇ�@@
                    //�����Y�]�͏��<�����ڋq>.�]�͌v�Z����.�����~�敪 == "����\"�̏ꍇ�A
                    if (l_dblBalance < 0 && !l_powerCalcEquity.getCalcCondition().isTradingStop())
                    {
                        //���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h�̖߂�l��ԋp����B
                        log.exiting(STR_METHOD_NAME);
                        return l_powerCalcEquity.getCalcCondition().getBizDate(i);
                    }
                    */
					//�S�|�R�|�P�j���Y�]�͏��<�����ڋq>.calc���֋�()���R�[������B
					double l_dblBalance = l_powerCalcEquity.calcDebitAmount(i);
					//�S�|�R�|�Q�j�S�|�R�|�P�j�̌v�Z���ʂ�0���傫���ꍇ(0<�v�Z����)�A�@@
					//�����Y�]�͏��<�����ڋq>.�]�͌v�Z����.�����~�敪 == "����\"�̏ꍇ�A
					if (l_dblBalance > 0 && !l_powerCalcEquity.getCalcCondition().isTradingStop())
					{
						//���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h�̖߂�l��ԋp����B
						log.exiting(STR_METHOD_NAME);
						return l_powerCalcEquity.getCalcCondition().getBizDate(i);
					}
                    
                }
                
            }
        }
        //�T�j�S�j�ȊO�̏ꍇ�A�ȉ��̏��������{����B
        else
        {
            //�T�|�P�j�P�j�̖߂�l�����Y�]�͏��<�M�p�ڋq>�^�ɃL���X�g����B
            WEB3TPTradingPowerCalcMargin l_powerCalcMargin = (WEB3TPTradingPowerCalcMargin) l_objPowerInfo;
            //�T�|�Q�j�ȉ��̏������A�C���f�b�N�X�͈̔�(0�`5)�ɂ��ČJ��Ԃ��B
            for (int i = 0; i <= WEB3PvInfoTPBizDateCheckNumDef.MAX_DAYS; i++)
            {
            	/*
                //�T�|�Q�|�P�j���Y�]�͏��<�M�p�ڋq>.calc�g�p�\����()���R�[������B
                double l_dblBalance = l_powerCalcMargin.calcActualAccountBalance(i);
                //�T�|�Q�|�Q�j�T�|�Q�|�P�j�̌v�Z���ʂ��}�C�i�X(<0)�������ꍇ�@@����
                //*���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.is�����~�敪() == false(����\)�̏ꍇ�A
                if (l_dblBalance < 0 && !l_powerCalcMargin.getCalcCondition().isTradingStop())
                {
                    //���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h�̖߂�l��ԋp����B
                    log.exiting(STR_METHOD_NAME);
                    return l_powerCalcMargin.getCalcCondition().getBizDate(i);
                }
                */
				//�T�|�Q�|�P�j���Y�]�͏��<�M�p�ڋq>.calc���֋�()���R�[������B
				double l_dblBalance = l_powerCalcMargin.calcDebitAmount(i);
				//�T�|�Q�|�Q�j�T�|�Q�|�P�j�̌v�Z���ʂ�0���傫���ꍇ(0<�v�Z����)�A�@@����
				//*���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.is�����~�敪() == false(����\)�̏ꍇ�A
				if (l_dblBalance > 0 && !l_powerCalcMargin.getCalcCondition().isTradingStop())
				{
					//���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get�c�Ɠ�()���\�b�h�̖߂�l��ԋp����B
					log.exiting(STR_METHOD_NAME);
					return l_powerCalcMargin.getCalcCondition().getBizDate(i);
				}
                
            }
        }
        // �U�jnull��ԋp����B�@@�����֋����������Ă��Ȃ��ׁB
        return null;
    }
    
    /**
     * (get�������)<BR>
     * �����̏��i�敪�̔���������v���擾����B<BR>
     * �������敪���킸�P�� * ���ʂ̐�Βl�̏W�v���s���B<BR>
     * �@@�萔���A����ł͊܂܂�Ȃ��B<BR>
     * �@@�����E���n�̑���͉��Z����Ȃ��B<BR>
     * �P�j�p�����[�^.���i�敪�ɂ��A�����Ώۂ̃e�[�u���������肷��B<BR>
     * �@@[�p�����[�^.���i�敪 == ("0�F����" or "1�F�M�p")�̏ꍇ]<BR>
     * �@@�@@�e�[�u���� = ���������P�ʃe�[�u��(eqtype_order_unit)<BR>
     * �@@[�p�����[�^.���i�敪 == ("2�F�敨 " or "3�F�I�v�V����")�̏ꍇ]<BR>
     * �@@�@@�e�[�u���� = �敨OP�����P�ʃe�[�u��(ifo_order_unit)<BR>
     * <BR>
     * �Q�j�����������擾����������p�����[�^.��������������ɒǉ�����B<BR>
     * �@@�p�����[�^.�������������� += " and biz_date = ?"<BR>
     * <BR>
     * �R�j�Ɩ����t(*1)�𕶎���ϊ�(�t�H�[�}�b�g�FyyyyMMdd)���A<BR>
     * �@@�p�����[�^.���������f�[�^�R���e�i�ɒǉ�����B<BR>
     * <BR>
     * �S�j���i�𔻕ʂ���������p�����[�^.��������������&<BR>
     * �@@�@@�p�����[�^.���������f�[�^�R���e�i�ɒǉ�����B<BR>
     * �@@�p�����[�^.���i�敪���A<BR>
     * �@@["0�F����"�̏ꍇ]<BR>
     * �@@�@@�E�p�����[�^.�������������� += "and order_categ = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_type not in (?, ?)"<BR>
     * �@@�@@�E�p�����[�^.���������f�[�^�R���e�i�Ɉȉ��̒l��ǉ��B<BR>
     * �@@�@@�@@�@@�E"1�F��������"<BR>
     * �@@�@@�@@�@@�E"101�F�����~�j����������"<BR>
     * �@@�@@�@@�@@�E"102�F�����~�j����������"<BR>
     * �@@["1�F�M�p"�̏ꍇ]<BR>
     * �@@�@@�E�p�����[�^.�������������� += "and order_categ not in (?, ?) "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_type not in (?, ?)"<BR>
     * �@@�@@�E�p�����[�^.���������f�[�^�R���e�i�Ɉȉ��̒l��ǉ��B<BR>
     * �@@�@@�@@�@@�E"1�F��������"<BR>
     * �@@�@@�@@�@@�E"7�F�����E���n����"<BR>
     * �@@�@@�@@�@@�E"101�F�����~�j����������"<BR>
     * �@@�@@�@@�@@�E"102�F�����~�j����������"<BR>
     * �@@["2�F�敨"�̏ꍇ]<BR>
     * �@@�@@�E�p�����[�^.�������������� += "and future_option_div = ? "<BR>
     * �@@�@@�E�p�����[�^.���������f�[�^�R���e�i��"1�F�敨"(�敨�^�I�v�V�����敪)��ǉ��B<BR>
     * �@@["3�F�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�E�p�����[�^.�������������� += "and future_option_div = ? "<BR>
     * �@@�@@�E�p�����[�^.���������f�[�^�R���e�i��"2�F�I�v�V����"(�敨�^�I�v�V�����敪)��ǉ��B<BR>
     * <BR>
     * �T�jQueryProcessor.doFindAllQuery()�ɂāA�����P�ʂ��擾����B<BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�P�j�ɂĎ擾�����e�[�u����<BR>
     * �@@arg1�F�@@��L������ǉ������p�����[�^.��������������<BR>
     * �@@arg4�F�@@��L������ǉ������p�����[�^.���������f�[�^�R���e�i<BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́A0��ԋp����B<BR>
     * <BR>
     * �U�j����������v���Z�o����B<BR>
     * �@@�T�j�̖߂�l�̗v�f(=�����P��Row)�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�U�|�P�jinstanceof�ɂď����Ώۂ̒����P��Row�̌^���ȉ��̂ǂ���Ȃ̂��𔻕ʂ��A<BR>
     * �@@�@@�Ή�����^�ɃL���X�g����B<BR>
     * �@@�@@�@@�E���������P��Row<BR>
     * �@@�@@�@@�E�敨OP�����P��Row<BR>
     * �@@�@@�U�|�P�|�P�j�L���X�g���������P��Row���A�ȉ��̍��ڒl���擾����B<BR>
     * �@@�@@�@@�E����ID<BR>
     * �@@�@@�@@�E�����P��ID<BR>
     * �@@�@@�@@�E��������<BR>
     * �@@�@@�@@�E�����P��<BR>
     * �@@�@@�@@�E��萔�� ��null�̏ꍇ�́A0�Ƃ���B<BR>
     * �@@�@@�@@�E�w���搔(*2)<BR>
     * �@@�U�|�Q�j����ςłȂ����(*3)�̂݁A����蕪�̔���������W�v����B<BR>
     * �@@�@@�@@������� = �����P�� * (�������� - ��萔��)<BR>
     * �@@�U�|�R�j��萔�� != 0�̏ꍇ�A��蕪�̔���������W�v����B<BR>
     * �@@�@@�@@�����P��ID�ɊY������g�����U�N�V�������擾����B<BR>
     * �@@�@@�@@this.get�g�����U�N�V�����ꗗ()���\�b�h���R�[������B<BR>
     * �@@�@@�@@[get�g�����U�N�V�����ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@rowType�F�@@<BR>
     * �@@�@@�@@�@@�@@[�p�����[�^.���i�敪 == ("0�F����" or "1�F�M�p")�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@EqtypeFinTransactionRow.TYPE<BR>
     * �@@�@@�@@�@@�@@[�p�����[�^.���i�敪 == ("2�F�敨 " or "3�F�I�v�V����")�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@IfoFinTransactionRow.TYPE���Z�b�g�B<BR>
     * �@@�@@�@@�@@����ID�F�@@����ID<BR>
     * �@@�@@�@@�@@�����P��ID�F�@@�����P��ID<BR>
     * �@@�@@�U�|�R�|�P�jget�g�����U�N�V����()�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�@@�@@instanceof�ɂď����Ώۂ̗v�f�̌^���ȉ��̂ǂ���Ȃ̂��𔻕ʂ��A<BR>
     * �@@�@@�@@�@@�Ή�����^�ɃL���X�g����B�L���X�g������A�ȉ��̌v�Z���ɂ�蔄������ɉ��Z����B<BR>
     * �@@�@@�@@�@@�@@�EEqtypeFinTransactionRow<BR>
     * �@@�@@�@@�@@�@@�EIfoFinTransactionRow<BR>
     * �@@�@@�@@�@@������� += �L���X�g�����g�����U�N�V����Row.���P�� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�L���X�g�����g�����U�N�V����Row.��萔<BR>
     *     �U�|�S�j�p�����[�^.���i�敪 == ("2�F�敨 " or "3�F�I�v�V����")�̏ꍇ�A<BR>
     * �@@�@@�@@��������Ɏw���搔��������B<BR>
     * �@@�@@�@@������� = ������� * �w���搔<BR>
     * �@@�U�|�T�j����������v�ɉ��Z����B<BR>
     * �@@�@@�@@����������v += �������<BR>
     * <BR>
     * �V�j����������v��ԋp����B<BR>
     * (*1)�Ɩ����t<BR>
     * �@@GtlUtils.getTradingSystem().getBizDate()�ɂĎ擾�����Ɩ����t<BR>
     * (*2)�敨OP�����P��Row�^�ɃL���X�g�����ꍇ�́A<BR>
     * �@@this.get�w���搔()�ɂĎw���搔���擾����B<BR>
     * �@@[get�w���搔()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@���XID�F�@@�L���X�g���������P��Row.���XID<BR>
     * �@@�@@�s��ID�F�@@�L���X�g���������P��Row.�s��ID<BR>
     * �@@�@@����ID�F�@@�L���X�g���������P��Row.����ID<BR>
     * (*3)����ςłȂ�����F 
     * �@@���������E����敪��2�i�ꕔ��������j�łȂ����
     * @@param l_strProductDivDiv<BR>
     * @@param l_strQueryString<BR>
     * @@param l_strQueryDataContainers<BR>
     * @@return<BR>
     */
    public double getTradePrice(String l_strProductDivDiv,  String l_strQueryString, String[] l_strQueryDataContainers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradePrice(String l_strProductDivDef,  String l_strQueryString, String[] l_strQueryDataContainers)";
        log.entering(STR_METHOD_NAME);
        //�P�j�p�����[�^.���i�敪�ɂ��A�����Ώۂ̃e�[�u���������肷��B
        //[�p�����[�^.���i�敪 == ("0�F����" or "1�F�M�p")�̏ꍇ]
        //�e�[�u���� = ���������P�ʃe�[�u��(eqtype_order_unit)
        if (l_strQueryString == null || l_strQueryDataContainers == null)
        {
            log.error("�p�����[�^.Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.Null�o���Ȃ��B");
        }
        RowType l_rowType = null;
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDivDiv)
                || WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDivDiv))
        {
            l_rowType = EqtypeOrderUnitRow.TYPE;
        }
        //�@@[�p�����[�^.���i�敪 == ("2�F�敨 " or "3�F�I�v�V����")�̏ꍇ]
        //�e�[�u���� = �敨OP�����P�ʃe�[�u��(ifo_order_unit)
        else if (WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDivDiv)
                || WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDivDiv))
        {
            l_rowType = IfoOrderUnitRow.TYPE;
        }
        //�Q�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQueryString = new StringBuffer();
        //�Q�j�����������擾����������p�����[�^.��������������ɒǉ�����B
        l_sbQueryString.append(l_strQueryString);
        l_sbQueryString.append(" and biz_date = ?");
        //�R�j�Ɩ����t(*1)�𕶎���ϊ�(�t�H�[�}�b�g�FyyyyMMdd)���A
        //�p�����[�^.���������f�[�^�R���e�i�ɒǉ�����B
        //(*1)�Ɩ����tGtlUtils.getTradingSystem().getBizDate()�ɂĎ擾�����Ɩ����t
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        
        List l_lisQueryVars = new ArrayList();
        for(int i = 0; i < l_strQueryDataContainers.length; i++)
        {
            l_lisQueryVars.add(l_strQueryDataContainers[i]);
        } 
        //�t�H�[�}�b�g�FyyyyMMdd
        l_datBizDate = WEB3DateUtility.toDay(l_datBizDate);
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");

        //�p�����[�^.���������f�[�^�R���e�i�ɒǉ�
        l_lisQueryVars.add(l_strBizDate);
        
        //�S�j���i�𔻕ʂ���������p�����[�^.��������������&�p�����[�^.���������f�[�^�R���e�i�ɒǉ�����B
        //�@@�p�����[�^.���i�敪���A["0�F����"�̏ꍇ] �E�p�����[�^.�������������� += "and order_categ = ? "
        //+ "and order_type not in (?, ?)"
        
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDivDiv))
        {
            l_sbQueryString.append("and order_categ = ? and order_type not in (?, ?)");
            
            //�@@�@@�E�p�����[�^.���������f�[�^�R���e�i�Ɉȉ��̒l��ǉ��B
           //�E"1�F��������"
          //�E"101�F�����~�j����������"
         //�E"102�F�����~�j����������"
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }
        //�@@["1�F�M�p"�̏ꍇ]�E�p�����[�^.�������������� +=
        // "and order_categ not in (?, ?) "+ "and order_type not in (?, ?)"
        else if (WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDivDiv))
        {
            l_sbQueryString.append("and order_categ not in (?, ?) "+ "and order_type not in (?, ?)");
            
            //�@@�@@�E�p�����[�^.���������f�[�^�R���e�i�Ɉȉ��̒l��ǉ��B
            //�E"1�F��������"
            //�E"7�F�����E���n����"
            //�E"101�F�����~�j����������"
            //�E"102�F�����~�j����������"
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderCategEnum.SWAP_MARGIN);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }
        
        //�@@["2�F�敨"�̏ꍇ]
        //�E�p�����[�^.�������������� += "and future_option_div = ? "
        //�E�p�����[�^.���������f�[�^�R���e�i��"1�F�敨"(�敨�^�I�v�V�����敪)��ǉ��B
        else if (WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDivDiv))
        {
            l_sbQueryString.append("and future_option_div = ? ");
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.FUTURES);
        }
        
        //�@@["3�F�I�v�V����"�̏ꍇ]
        //�E�p�����[�^.�������������� += "and future_option_div = ? "
        //�E�p�����[�^.���������f�[�^�R���e�i��"2�F�I�v�V����"(�敨�^�I�v�V�����敪)��ǉ��B
        else if (WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDivDiv))
        {
            l_sbQueryString.append("and future_option_div = ? ");
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.OPTION);
        }
        
        //�T�jQueryProcessor.doFindAllQuery()�ɂāA�����P�ʂ��擾����B
        //�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]
        //arg0�F�@@�P�j�ɂĎ擾�����e�[�u����
        //arg1�F�@@��L������ǉ������p�����[�^.��������������
        //arg4�F�@@��L������ǉ������p�����[�^.���������f�[�^�R���e�i
        List l_lstQuery = new ArrayList();
        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_lisQueryVars.size()];
            l_lisQueryVars.toArray(l_objVars);

            log.debug("��������������: " + l_sbQueryString.toString());
            log.debug("���������f�[�^�R���e�i: " + l_lisQueryVars.toString());

            l_lstQuery = l_queryProcessor.doFindAllQuery(
                l_rowType,
                l_sbQueryString.toString(),
                l_objVars);
        }
        catch (DataFindException l_ex)
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
        //�������ʂ��擾�ł��Ȃ������ꍇ�́A0��ԋp����B
        if (l_lstQuery == null || l_lstQuery.size() == 0)
        {
            return 0;
        }
        //�U�j����������v���Z�o����B
        //�T�j�̖߂�l�̗v�f(=�����P��Row)�����A�ȉ��̏������J��Ԃ��B
        //�@@�U�|�P�jinstanceof�ɂď����Ώۂ̒����P��Row�̌^���ȉ��̂ǂ���Ȃ̂��𔻕ʂ��A
        //�Ή�����^�ɃL���X�g����
        //�E���������P��Row
        //�E�敨OP�����P��Row
        int l_intSize = l_lstQuery.size();
        //�������
        double l_dblTradePrice = 0D;                
        for (int i = 0; i < l_intSize; i++)
        {
            if (l_lstQuery.get(i) instanceof EqtypeOrderUnitRow)
            {
                
                //�U�|�P�|�P�j�L���X�g���������P��Row���A�ȉ��̍��ڒl���擾����B
                EqtypeOrderUnitRow l_eqtypeRow = (EqtypeOrderUnitRow)l_lstQuery.get(i);
                //����ID
                long l_lngOrderId = l_eqtypeRow.getOrderId();
                log.debug("����ID: " + l_lngOrderId);
                //�����P��ID
                long l_lngOrderUnitId = l_eqtypeRow.getOrderUnitId();
                log.debug("�����P��ID: " + l_lngOrderUnitId);                
                //��������
                double l_dblQuantity = l_eqtypeRow.getQuantity();
                log.debug("��������: " + l_dblQuantity);              
                //�����P��
                double l_dblPrice = l_eqtypeRow.getPrice();
                log.debug("�����P��: " + l_dblPrice);   
                //��萔�� ��null�̏ꍇ�́A0�Ƃ���B
                double l_dblExecutedQuantity = 0D;
                if (!l_eqtypeRow.getExecutedQuantityIsNull())
                {
                    l_dblExecutedQuantity = l_eqtypeRow.getExecutedQuantity();
                } 
                log.debug("��萔��: " + l_dblExecutedQuantity);   

                //�@@�U�|�Q�j����ςłȂ�����̂݁A����蕪�̔���������W�v����B������� = �����P�� * (�������� - ��萔��) 
                double l_dblEqtypeTradePrice = 0D;
                if (!WEB3ModifyCancelTypeDef.PART_CANCELED.equals(l_eqtypeRow.getModifyCancelType()))
                {
                    l_dblEqtypeTradePrice = l_dblPrice * (l_dblQuantity - l_dblExecutedQuantity);
                    log.debug("����蕪������: " + l_dblEqtypeTradePrice);
                }
                    
                //�U�|�R�j��萔�� != 0�̏ꍇ�A��蕪�̔���������W�v����B
                double l_dbEqtypelexecutedTradePrice = 0D;
                if (l_dblExecutedQuantity != 0)
                {
                    //�����P��ID�ɊY������g�����U�N�V�������擾����B
                    //this.get�g�����U�N�V�����ꗗ()���\�b�h���R�[������B
                    List l_lstFinTransactions = this.getFinTransactionList(EqtypeFinTransactionRow.TYPE,l_lngOrderId,l_lngOrderUnitId);
                    //�U�|�R�|�P�jget�g�����U�N�V����()�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B
                    int l_intFinTransactionLenght = 0;
                    if (l_lstFinTransactions != null)
                    {
                        l_intFinTransactionLenght = l_lstFinTransactions.size();
                    }
                    
                    for (int k = 0; k < l_intFinTransactionLenght; k++)
                    {
                        EqtypeFinTransactionRow l_row = (EqtypeFinTransactionRow)l_lstFinTransactions.get(k);
                        //������� += �L���X�g�����g�����U�N�V����Row.���P�� *�L���X�g�����g�����U�N�V����Row.��萔��
                        l_dbEqtypelexecutedTradePrice += l_row.getPrice() * l_row.getQuantity();
                        log.debug("��蔄����: " + l_dbEqtypelexecutedTradePrice);
                    }
                }   
                //����������v += �������
                l_dblTradePrice += (l_dblEqtypeTradePrice + l_dbEqtypelexecutedTradePrice); 
                log.debug("����������v: " + l_dblTradePrice);            
            }
            else if (l_lstQuery.get(i) instanceof IfoOrderUnitRow)
            {
                //�U�|�P�|�P�j�L���X�g���������P��Row���A�ȉ��̍��ڒl���擾����B
                IfoOrderUnitRow l_ifoRow = (IfoOrderUnitRow)l_lstQuery.get(i);
                //����ID
                long l_lngOrderId = l_ifoRow.getOrderId();
                log.debug("����ID: " + l_lngOrderId); 
                //�����P��ID
                long l_lngOrderUnitId = l_ifoRow.getOrderUnitId();
                log.debug("�����P��ID: " + l_lngOrderUnitId);                 
                //��������
                double l_dblQuantity = l_ifoRow.getQuantity();
                log.debug("��������: " + l_dblQuantity); 
                //�����P��
                double l_dblPrice = l_ifoRow.getPrice();
                log.debug("�����P��: " + l_dblPrice); 
                //��萔�� ��null�̏ꍇ�́A0�Ƃ���B
                double l_dblExecutedQuantity = 0D;
                
                if (!l_ifoRow.getExecutedQuantityIsNull())
                {
                    l_dblExecutedQuantity = l_ifoRow.getExecutedQuantity();
                } 
                log.debug("��萔��: " + l_dblExecutedQuantity); 
                //�w���搔(*2)            

                //�@@�U�|�Q�j����ςłȂ�����̂݁A����蕪�̔���������W�v����B������� = �����P�� * (�������� - ��萔��) 
                double l_dblIfoTradePrice = 0D;
                if (!WEB3ModifyCancelTypeDef.PART_CANCELED.equals(l_ifoRow.getModifyCancelType()))
                {
                    l_dblIfoTradePrice = l_dblPrice * (l_dblQuantity - l_dblExecutedQuantity);
                    log.debug("����蕪�̔���������W�v����B: " + l_dblIfoTradePrice); 
                }
                
                //�U�|�R�j��萔�� != 0�̏ꍇ�A��蕪�̔���������W�v����B
                double l_dblIfoExecutedTradePrice = 0D;
                if (l_dblExecutedQuantity != 0)
                {
                    //�����P��ID�ɊY������g�����U�N�V�������擾����B
                    //this.get�g�����U�N�V�����ꗗ()���\�b�h���R�[������B
                    List l_lstFinTransactions = this.getFinTransactionList(IfoFinTransactionRow.TYPE,l_lngOrderId,l_lngOrderUnitId);
                    //�U�|�R�|�P�jget�g�����U�N�V����()�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B
                    int l_intFinTransactionLenght = 0;
                    if (l_lstFinTransactions != null)
                    {
                        l_intFinTransactionLenght = l_lstFinTransactions.size();
                    }
                    
                    for (int k = 0; k < l_intFinTransactionLenght; k++)
                    {
                        IfoFinTransactionRow l_row = (IfoFinTransactionRow)l_lstFinTransactions.get(k);
                        //������� += �L���X�g�����g�����U�N�V����Row.���P�� *�L���X�g�����g�����U�N�V����Row.��萔��
                        log.debug("�L���X�g�����g�����U�N�V����Row.���P�� : " + l_row.getPrice()); 
                        log.debug("�L���X�g�����g�����U�N�V����Row.��萔�� : " + l_row.getQuantity()); 
                        l_dblIfoExecutedTradePrice += l_row.getPrice() * l_row.getQuantity();
                    }
                }
                
                //(*2)�敨OP�����P��Row�^�ɃL���X�g����
                //this.get�w���搔()�ɂĎw���搔���擾����B
                double l_dblUnitSize = this.getUnitSize(l_ifoRow.getBranchId(),l_ifoRow.getMarketId(),l_ifoRow.getProductId());
                log.debug("�w���搔: " + l_dblUnitSize);
                //����������v += �������
                l_dblTradePrice += (l_dblIfoTradePrice + l_dblIfoExecutedTradePrice) * l_dblUnitSize;
                log.debug("����������v: " + l_dblTradePrice);  
            }
        }
        log.exiting(STR_METHOD_NAME);
        //�V�j����������v��ԋp����B
        return l_dblTradePrice;
    }
    /**
     * (get�g�����U�N�V�����ꗗ)<BR>
     * �g�����U�N�V�����̈ꗗ���擾����B<BR>
     * �P�j�ȉ��̏����ɊY������f�[�^��<BR>
     * �p�����[�^.rowType�ɊY������g�����U�N�V�����e�[�u������擾����B<BR>
     * (doFindAllQuery���g�p)<BR>
     * <BR>
     * [����]<BR>
     * ����ID�@@=�@@�p�����[�^.����ID�@@����<BR>
     * �����P��ID�@@=�@@�p�����[�^.�����P��ID�@@����<BR>
     * �폜�t���O�@@=�@@FALSE<BR>
     * �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B<BR>
     * @@param l_rowType
     * @@param l_lngOrderId
     * @@param l_lngOrderUnitId
     * @@return
     */
    public List getFinTransactionList(RowType l_rowType, long l_lngOrderId, long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getFinTransactionList(RowType l_rowType, long l_lngOrderId, long l_lngOrderUnitId)";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            String l_strWhere = "order_id = ? and order_unit_id = ? and delete_flag= ? ";
            QueryProcessor l_qp = l_qp = Processors.getDefaultProcessor();
            List l_lisRows = null;
            Object[] l_objWhereValues = { new Long(l_lngOrderId), new Long(l_lngOrderUnitId), BooleanEnum.FALSE};
            l_lisRows = l_qp.doFindAllQuery(l_rowType, l_strWhere, l_objWhereValues);
            
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            log.exiting(STR_METHOD_NAME);
            return l_lisRows;
        }
        catch (DataNetworkException l_dnwe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
    }
    /**
     * (get�c�Ɠ��ꗗ)<BR>
     * �c�Ɠ��̈ꗗ��ԋp����B<BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j���t�̃Z�b�g<BR>
     * �@@�EArrayList�ɋƖ����t(*1)�̓��t����(YYYYMMDD)��ǉ�����B<BR>
     * �@@�EArrayList�ɋƖ����t�̗��c�Ɠ��̓��t����(YYYYMMDD)��ǉ�����B<BR>
     * <BR>
     * �R�jArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@return<BR>
     */
    public Date[] getBizDateList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBizDateList()";
        log.entering(STR_METHOD_NAME);
        //�P�jArrayList�𐶐�����B
        List l_datBizDateList = new ArrayList();
        
        //�Q�j���t�̃Z�b�g
        //ArrayList�ɋƖ����t(*1)�̓��t����(YYYYMMDD)��ǉ�����B
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        l_datBizDateList.add(WEB3DateUtility.toDay(l_datBizDate));
        //ArrayList�ɋƖ����t�̗��c�Ɠ��̓��t����(YYYYMMDD)��ǉ�����B
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        l_datBizDateList.add(WEB3DateUtility.toDay(l_bizDate.roll(1)));
        
        // �R�jArrayList.toArray()�̖߂�l��ԋp����B
        Date[] l_datBizDates = new Date[l_datBizDateList.size()];
        l_datBizDateList.toArray(l_datBizDates);
        
        log.exiting(STR_METHOD_NAME);
        return l_datBizDates;
    }
    
    /**
     * (get�w���搔)<BR>
     * �w���搔���擾���ԋp����B<BR>
     * �P�j����J�����_�R���e�L�X�g��敨OP�̐ݒ�ɂ���B<BR>
     * �@@�ȉ��̒l�Ŏ���J�����_�R���e�L�X�g�����Z�b�g����B<BR>
     * �@@�����Z�b�g�O�̊e�l�͑ޔ������Ă������ƁB<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = "0�F���̑�"<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �p�����[�^����ID�ɊY������敨OP�����I�u�W�F�N�g,get�����Y�����R�[�h()<BR>
     * �Q�j�敨OP��������̎擾<BR>
     * �@@�敨OP�v���_�N�g�}�l�[�W��.get�������()�ɂ�<BR>
     * �@@�敨OP����������擾����B<BR>
     * �@@[get�������()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЁF�@@�p�����[�^.���XID�ɊY�����镔�X.getInstitution()<BR>
     * �@@�@@�����R�[�h�F�@@�擾�����敨OP�����I�u�W�F�N�g.�����R�[�h<BR>
     * �@@�@@�s��R�[�h�F�@@�p�����[�^.�s��ID�ɊY������s��.�s��R�[�h<BR>
     * �R�j����J�����_�R���e�L�X�g����ײ�ްĲ�̫Ұ��݂̐ݒ�ɖ߂��B<BR>
     * �@@�P�j�ɂđޔ��������l�ŁA����J�����_�R���e�L�X�g�̃��Z�b�g���s���B<BR>
     * �S�j�擾�����敨OP�������.�P�P�ʓ���搔��ԋp����B<BR>
     * @@param l_lngBranchId<BR>
     * @@param l_lngMarketId<BR>
     * @@param l_lngProductId<BR>
     * @@return<BR>
     */
    public double getUnitSize(long l_lngBranchId,long l_lngMarketId, long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUnitSize(long l_lngBranchId,long l_lngMarketId, long l_lngProductId)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j����J�����_�R���e�L�X�g��敨OP�̐ݒ�ɂ���B
        //�ȉ��̒l�Ŏ���J�����_�R���e�L�X�g�����Z�b�g����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_manager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();     
        WEB3GentradeTradingClendarContext l_context = (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //�Q�j�敨OP��������̎擾
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

        WEB3GentradeAccountManager l_accountManger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();    
        
        String l_strOldMaketCode = l_context.getMarketCode();
        String l_strOldTradingTimeType = l_context.getTradingTimeType();
        String l_strOldProductCode = l_context.getProductCode();
        try
        {
            //����J�����_�R���e�L�X�g.�s��R�[�h = "0�F���̑�"
            String l_strMaketCode = l_manager.getMarket(l_lngMarketId).getMarketCode();
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

            
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_productManager.getProduct(l_lngProductId);
            //����J�����_�R���e�L�X�g.�����R�[�h = �p�����[�^����ID�ɊY������敨OP�����I�u�W�F�N�g,get�����Y�����R�[�h()
            l_context.setProductCode(l_product.getUnderlyingProductCode());
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
            
            //�،���ЁF�@@�p�����[�^.���XID�ɊY�����镔�X.getInstitution()
            Institution l_institution = l_accountManger.getBranch(l_lngBranchId).getInstitution();
            // �敨OP�v���_�N�g�}�l�[�W��.get�������()�ɂ�
            WEB3IfoTradedProductImpl l_tradedProduct = l_productManager.getIfoTradedProduct(l_institution,l_product.getProductCode(),l_strMaketCode);
           
            //�R�j����J�����_�R���e�L�X�g����ײ�ްĲ�̫Ұ��݂̐ݒ�ɖ߂�
            l_context.setMarketCode(l_strOldMaketCode);
            l_context.setTradingTimeType(l_strOldTradingTimeType);
            l_context.setProductCode(l_strOldProductCode);
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
           
            //�S�j�擾�����敨OP�������.�P�P�ʓ���搔��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_tradedProduct.getUnitSize();
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (is���ʋ�������)<BR>
     * ���������̑ΏۂƂȂ錚�ʂ�<BR> 
     * �ۗL���Ă��邩���ʂ���B<BR>
     * <BR>
     * �ۗL���Ă���ꍇtrue�A�ȊOfalse��ԋp����B<BR> 
     * <BR>
     * �P�jthis.get���������Ǘ��M�pParams���R�[������B<BR> 
     * <BR>
     * �@@[get���������Ǘ��M�pParams�ɃZ�b�g����p�����[�^]<BR>  
     * �@@�@@�ڋq�F�@@�ڋq�I�u�W�F�N�g <BR>
     * <BR>
     * �Q�j�P�j�̎擾���ʂ�null�̏ꍇ�Afalse��ԋp����B<BR> 
     * <BR>
     * �R�j�P�j�̎擾���ʂ��玟�̒l���擾����<BR> 
     * �@@20%������� = ���������Ǘ��M�pParams.20%���ꔭ���o�ߓ���<BR> 
     * �@@30%������� = ���������Ǘ��M�pParams.30%���ꔭ���o�ߓ���<BR>
     * <BR>
     * �S�j20%��������A30%����������狭�������̑Ώۂ����f����<BR> 
     * <BR>
     * �@@�S�|�P�j�p�����[�^.�\�������ԍ� = "1041" ����<BR> 
     * �@@�@@�@@�@@�@@20%������� = 1 ���� <BR>
     * �@@�@@�@@�@@�@@30%������� <= 5 �̏ꍇ�Atrue ��ԋp����B<BR> 
     * �@@�S�|�Q�j�p�����[�^.�\�������ԍ� = "1042" ���� <BR>
     * �@@�@@�@@�@@�@@20%������� = 1 ���� <BR>
     * �@@�@@�@@�@@�@@30%������� = 6 �̏ꍇ�Atrue ��ԋp����B<BR> 
     * �@@�S�|�R�j�p�����[�^.�\�������ԍ� = "1043" ���� <BR>
     * �@@�@@�@@�@@�@@20%������� = 2 ���� <BR>
     * �@@�@@�@@�@@�@@30%������� <= 6 �̏ꍇ�Atrue ��ԋp����B<BR> 
     * �@@�S�|�S�j�p�����[�^.�\�������ԍ� = "1044" ���� <BR>
     * �@@�@@�@@�@@�@@20%������� >= 3 �̏ꍇ�Atrue ��ԋp����B<BR> 
     * �@@�S�|�T�j�p�����[�^.�\�������ԍ� = "1045" ���� <BR>
     * �@@�@@�@@�@@�@@30%������� >= 2 ���� <BR>
     * �@@�@@�@@�@@�@@30%������� <= 4 �̏ꍇ�Atrue ��ԋp����B<BR> 
     * �@@�S�|�U�j�p�����[�^.�\�������ԍ� = "1046" ���� <BR>
     * �@@�@@�@@�@@�@@30%������� = 5 �̏ꍇ�Atrue ��ԋp����B<BR> 
     * �@@�S�|�V�j�p�����[�^.�\�������ԍ� = "1047" ���� <BR>
     * �@@�@@�@@�@@�@@30%������� = 6 �̏ꍇ�Atrue ��ԋp����B<BR> 
     * �@@�S�|�W�j�p�����[�^.�\�������ԍ� = "1048" ����<BR> 
     * �@@�@@�@@�@@�@@30%������� >= 7 �̏ꍇ�Atrue ��ԋp����B<BR> 
     * <BR>
     * �@@��L�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strConditionNo - (�\�������ԍ�)
     * �\�������ݒ�Params.�\�������ԍ�<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isContractEnforcedDisposal(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strConditionNo)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isContractEnforcedDisposal(WEB3GentradeMainAccount l_mainAccount, String l_strConditionNo)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jthis.get���������Ǘ��M�pParams���R�[������B
        PaymentRequisitionMarginRow l_marginRow = this.getPaymentRequisitionMarginParams(l_mainAccount);
        
        //�Q�j�P�j�̎擾���ʂ�null�̏ꍇ�Afalse��ԋp����B
        if (l_marginRow == null) 
        {
            return false;
        }

        //�R�j�P�j�̎擾���ʂ��玟�̒l���擾����
        //20%������� = ���������Ǘ��M�pParams.20%���ꔭ���o�ߓ���
        String l_strBreak20ElapsedDays = l_marginRow.getBreak20elapsedDays();
        
        //30%������� = ���������Ǘ��M�pParams.30%���ꔭ���o�ߓ���
        String l_strBreak30ElapsedDays = l_marginRow.getBreak30elapsedDays();
        
        //�S�j20%��������A30%����������狭�������̑Ώۂ����f����
        //�S�|�P�j�p�����[�^.�\�������ԍ� = "1041" ����20%������� = 1 ����
        //      30%������� <= 5 �̏ꍇ�Atrue ��ԋp����B
        if (l_strBreak20ElapsedDays == null || l_strBreak30ElapsedDays == null)
        {
            return false;
        }
        int l_intBreak20ElapsedDays = Integer.parseInt(l_strBreak20ElapsedDays);
        int l_intBreak30ElapsedDays = Integer.parseInt(l_strBreak30ElapsedDays);
        
        if (WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN.equals(l_strConditionNo) &&
            l_intBreak20ElapsedDays == 1 &&
            l_intBreak30ElapsedDays <= 5)
        {
            log.exiting(STR_METHOD_NAME);
            return true; 
        }
        
        //�S�|�Q�j�p�����[�^.�\�������ԍ� = "1042" ���� 20%������� = 1 ����
        //30%������� = 6 �̏ꍇ�Atrue ��ԋp����B
        else if (WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY.equals(l_strConditionNo) &&
            l_intBreak20ElapsedDays == 1 &&
            l_intBreak30ElapsedDays == 6) 
        {
            log.exiting(STR_METHOD_NAME);
            return true; 
        }
        
        //�S�|�R�j�p�����[�^.�\�������ԍ� = "1043" ���� 20%������� = 2 ����
        //30%������� <= 6 �̏ꍇ�Atrue ��ԋp����B
        else if (WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN.equals(l_strConditionNo) &&
            l_intBreak20ElapsedDays == 2 &&
            l_intBreak30ElapsedDays <= 6) 
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�S�|�S�j�p�����[�^.�\�������ԍ� = "1044" ����20%������� >= 3 �̏ꍇ�Atrue ��ԋp����B
        else if (WEB3PvInfoConditionDef.BREAK_3DAY_OVER.equals(l_strConditionNo) &&
            l_intBreak20ElapsedDays >= 3) 
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�@@�S�|�T�j�p�����[�^.�\�������ԍ� = "1045" ���� 30%������� >= 2 ���� 
        //�@@�@@�@@�@@�@@30%������� <= 4 �̏ꍇ�Atrue ��ԋp����B 
        else if (WEB3PvInfoConditionDef.BREAK_2TO4DAY.equals(l_strConditionNo) &&
            l_intBreak30ElapsedDays >= 2 &&
            l_intBreak30ElapsedDays <= 4)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�@@�S�|�U�j�p�����[�^.�\�������ԍ� = "1046" ���� 
        //�@@�@@�@@�@@�@@30%������� = 5 �̏ꍇ�Atrue ��ԋp����B
        else if (WEB3PvInfoConditionDef.BREAK_5DAY.equals(l_strConditionNo) &&
            l_intBreak30ElapsedDays == 5)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�@@�S�|�V�j�p�����[�^.�\�������ԍ� = "1047" ���� 
        //�@@�@@�@@�@@�@@30%������� = 6 �̏ꍇ�Atrue ��ԋp����B
        else if (WEB3PvInfoConditionDef.BREAK_6DAY.equals(l_strConditionNo) &&
            l_intBreak30ElapsedDays == 6)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�@@�S�|�W�j�p�����[�^.�\�������ԍ� = "1048" ���� 
        //�@@�@@�@@�@@�@@30%������� >= 7 �̏ꍇ�Atrue ��ԋp����B
        else if (WEB3PvInfoConditionDef.BREAK_7DAY_OVER.equals(l_strConditionNo) &&
            l_intBreak30ElapsedDays >= 7)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //��L�ȊO�̏ꍇ�Afalse��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (get���������Ǘ��M�pParams)<BR>
     * ���������Ǘ��M�pParams���擾����B<BR> 
     * <BR>
     * �P�j��������������&���������f�[�^�R���e�i<BR> 
     * �@@�ȉ��̌���������\���A��������������� <BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR> 
     * <BR>
     * �@@[����]<BR> 
     * �@@�@@����ID = �p�����[�^.�ڋq.����ID<BR> 
     * �@@�@@�v�Z�� = �Ɩ����t <BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR> 
     * <BR>
     * �@@�@@�������������� = " account_id = ? "<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " calc_date = ? "<BR> 
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR> 
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���Z�b�g����B <BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.getAccountId()�@@��������ϊ����ăZ�b�g�B<BR> 
     * �@@�@@�@@�EGtlUtils.getTradingSystem().getBizDate()�ɂĎ擾�����Ɩ����t<BR> 
     * �@@ <BR>
     * �Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR> 
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR> 
     * �@@arg0�F�@@���������Ǘ��e�[�u���i�M�p�j <BR>
     * �@@arg1�F�@@�쐬������������������ <BR>
     * �@@arg2�F�@@�쐬����ArrayList.toArray()<BR> 
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B<BR> 
     * <BR>
     * �R�j�Q�j�̌������ʂ�ԋp����B<BR> 
     * <BR>
     * @@param l_mainAccount - (�ڋq)
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return PaymentRequisitionMarginParams 
     * @@throws WEB3BaseException 
     */
    public PaymentRequisitionMarginParams getPaymentRequisitionMarginParams(
        WEB3GentradeMainAccount l_mainAccount)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getPaymentRequisitionMarginParams(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);        
              
        //�@@�P�|�P�j��L������������ɁA����������������쐬����B 
        //
        //�@@�@@�������������� = " account_id = ? " 
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " calc_date = ? " 
        String l_strQuery = " account_id = ? and calc_date = ? ";
        
        //�@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B 
        //�@@�@@ArrayList�𐶐����A�ȉ��̒l���Z�b�g����B 
        //�@@�@@�@@�E�p�����[�^.�ڋq.getAccountId()�@@��������ϊ����ăZ�b�g�B 
        //�@@�@@�@@�EGtlUtils.getTradingSystem().getBizDate()�ɂĎ擾�����Ɩ����t 
        List l_lisWhere = new ArrayList();
        l_lisWhere.add(String.valueOf(l_mainAccount.getAccountId()));
        l_lisWhere.add(GtlUtils.getTradingSystem().getBizDate()); 
        
        //�Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B 
        //
        //�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
        //�@@arg0�F�@@���������Ǘ��e�[�u���i�M�p�j 
        //�@@arg1�F�@@�쐬������������������ 
        //�@@arg2�F�@@�쐬����ArrayList.toArray()        
        List l_lisRecords = null;
        try 
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                PaymentRequisitionMarginRow.TYPE,
                l_strQuery,
                l_lisWhere.toArray());
        }
        catch (DataFindException l_ex)
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
        
        //�@@�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B 
        if (l_lisRecords == null || l_lisRecords.size() == 0)  
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�R�j�Q�j�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return (PaymentRequisitionMarginParams) l_lisRecords.get(0);
    }
    
    /**
     * (is�o����~)<BR>
     *�o����~�ڋq�����ʂ���B<BR>                 
     * <BR>                                        
     *�o����~�ڋq�̏ꍇtrue�A�ȊOfalse��ԋp����B<BR> 
     *�P�jthis.get�S�ەs���ڋq�f�[�^Params���R�[������B<BR> 
     *<BR>                                         
     *�@@[get�S�ەs���ڋq�f�[�^Params�ɃZ�b�g����p�����[�^]<BR>  
     *�@@�@@�ڋq�F�@@�ڋq�I�u�W�F�N�g <BR>            
     *�@@�@@�o����~�敪�F�@@�p�����[�^.�o����~�敪 <BR>
     *<BR>                                       
     *�Q�j�P�j�������ʂ��擾�ł����ꍇ��true�A<BR> 
     *�@@�擾�ł��Ȃ������ꍇ��false��ԋp����B<BR>
     *@@param l_mainAccount-(�ڋq)<BR>
     *�ڋq�I�u�W�F�N�g<BR>
     *@@param l_strCashoutStopDiv-�o����~�敪 <BR>
     *�o����~�敪<BR>
     *<BR>
     *1�F�S�z<BR>
     *2�F�ꕔ<BR>
     *@@return boolean 
     *@@throws WEB3BaseException
     */
    public boolean isCashoutStop(WEB3GentradeMainAccount l_mainAccount,
        String l_strCashoutStopDiv)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isCashoutStop(WEB3GentradeMainAccount l_mainAccount,String l_strCashoutStopDiv)";
        log.entering(STR_METHOD_NAME); 
        SecurityShortageAccountParams l_params = null;
        //�P�jthis.get�S�ەs���ڋq�f�[�^Params���R�[������B
        l_params =  this.getSecurityShortageAccountParams(l_mainAccount,l_strCashoutStopDiv);
        
        //�Q�j�P�j�������ʂ��擾�ł����ꍇ��true�A 
       //�擾�ł��Ȃ������ꍇ��false��ԋp����B
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get�S�ەs���ڋq�f�[�^Params)<BR>
     *�����ɊY������S�ەs���ڋq�f�[�^Params���擾����B<BR> 
     *<BR>
     *�P�j��������������&���������f�[�^�R���e�i<BR> 
     *�@@�ȉ��̌���������\���A��������������� <BR>
     *�@@ArrayList(���������f�[�^�R���e�i)���쐬����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�@@����ID = �p�����[�^.�ڋq.����ID <BR>
     *�@@�@@�o����~�敪 = �p�����[�^.�o����~�敪 <BR>
     *<BR>
     *�@@�P�|�P�j��L������������ɁA����������������쐬����B<BR> 
     *<BR>
     *�@@�@@�������������� = " account_id = ? " <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " payment_stop_div = ? " <BR>
     *<BR>
     *�@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B <BR>
     *�@@�@@ArrayList�𐶐����A�ȉ��̒l���Z�b�g����B <BR>
     *�@@�@@�@@�E�p�����[�^.�ڋq.getAccountId()�@@��������ϊ����ăZ�b�g�B <BR>
     *�@@�@@�@@�E�p�����[�^.�o����~�敪 <BR>
     *<BR>
     *�Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR> 
     *<BR>
     *�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     *�@@arg0�F�@@�S�ەs���ڋq�f�[�^�e�[�u�� <BR>
     *�@@arg1�F�@@�쐬������������������ <BR>
     *�@@arg2�F�@@�쐬����ArrayList.toArray() <BR>
     *<BR>
     *�@@�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B <BR>
     *<BR>
     *�R�j�Q�j�̌������ʂ�ԋp����B<BR>
      *@@param l_mainAccount-(�ڋq)<BR>
     *�ڋq�I�u�W�F�N�g<BR>
     *@@param l_strCashoutStopDiv-�o����~�敪 <BR>
     *�o����~�敪<BR>
     *<BR>
     *1�F�S�z<BR>
     *2�F�ꕔ<BR>
     *@@return �S�ەs���ڋq�f�[�^Params  
     *@@throws WEB3BaseException
     */
    public SecurityShortageAccountParams getSecurityShortageAccountParams(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strCashoutStopDiv)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getSecurityShortageAccountParams(WEB3GentradeMainAccount l_mainAccount,String l_strCashoutStopDiv)";
        log.entering(STR_METHOD_NAME); 
        
        //�P�|�P�j��L������������ɁA����������������쐬����B
        //�������������� = " account_id = ? " 
        //                       + " payment_stop_div = ? " 
        String l_strQuery = " account_id = ? and payment_stop_div = ? ";
        
        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B 
        //ArrayList�𐶐����A�ȉ��̒l���Z�b�g����B 
        //�E�p�����[�^.�ڋq.getAccountId()�@@��������ϊ����ăZ�b�g�B 
        //�E�p�����[�^.�o����~�敪 
        List l_lisWhere = new ArrayList();
        l_lisWhere.add(String.valueOf(l_mainAccount.getAccountId()));
        l_lisWhere.add(l_strCashoutStopDiv); 
        
        //�Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B 
        //[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
        //arg0�F�@@�S�ەs���ڋq�f�[�^�e�[�u�� 
        //arg1�F�@@�쐬������������������ 
        //arg2�F�@@�쐬����ArrayList.toArray() 
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                SecurityShortageAccountRow.TYPE,
                l_strQuery,
                l_lisWhere.toArray());

            log.debug("QueryProcessor.doFindByPrimaryKeyQuery()�����s���܂�");
        }
//        catch(DataFindException l_ex)
//        {
//            log.debug(STR_METHOD_NAME + l_ex.getMessage());
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }        
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
        //�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�R�j�Q�j�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return (SecurityShortageAccountParams) l_lisRecords.get(0);
    }
    
    /**
     * (is�萔�������L�����y�[��)<BR>
	 * �萔�������L�����y�[���Ώیڋq�����ʂ���B<BR>
	 * <BR>
	 * �萔�������L�����y�[���Ώیڋq�̏ꍇtrue�A�ȊOfalse��ԋp����B<BR>
     * �P�jthis.get�����L�����y�[���ڋqParams���R�[������B<BR>
     * <BR>
     * [get�����L�����y�[��Params�ɃZ�b�g����p�����[�^]<BR>
     * �ڋq�F�@@�ڋq�I�u�W�F�N�g<BR>
     * <BR>
     * �Q�j�P�j�������ʂ��擾�ł����ꍇ��true�A�擾�ł��Ȃ������ꍇ��false��ԋp����B<BR>
     * @@param l_mainAccount-(�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
	public boolean isAccInfoCampaign(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isAccInfoCampaign(WEB3GentradeMainAccount l_mainAccount)";
		log.entering(STR_METHOD_NAME);
		
		// �p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }
        
		// �P�jthis.get�����L�����y�[���ڋqParams���R�[������B
		CommCampaignAccHistoryParams [] l_commCampaignAccHistoryParams = 
			this.getCommCampaignAccHistoryParams(l_mainAccount);

		// �Q�j�P�j�������ʂ��擾�ł����ꍇ��true�A�擾�ł��Ȃ������ꍇ��false��ԋp����B
		if (l_commCampaignAccHistoryParams == null)
		{
			log.exiting(STR_METHOD_NAME);
			return false;
		}
		log.exiting(STR_METHOD_NAME);
		return true;
	}
	
    /**    
     * (get�����L�����y�[���ڋqParams)<BR>
     * �萔�������L�����y�[���ڋq����Params���擾����B<BR>
     * <BR>
     * �����ɊY������萔�������L�����y�[���ڋq����Params���擾����B<BR>
     * �P�j��������������&���������f�[�^�R���e�i�ȉ��̌���������\���A���������������<BR>
     * ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * [��������]<BR>
     * �ڋqID = �ڋq.getAccountId�i�j and <BR>
     * �Ώۊ���From <= ���X�c�Ɠ� and <BR>
     * �Ώۊ���To >= �����c�Ɠ� and <BR>
     * �L���敪 = �P�F�L��<BR>
     * <BR>
     * [�\�[�g����]<BR>
     * �Ώۊ���From.asc<BR>
     * <BR>
     * �Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * �Q�|�P�j�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B<BR>
     * �Q�|�Q�j�擾����List���Ɂy�o�^�^�C�v="�Q�F�����ʌڋq�w��"�z��<BR>
     *        �f�[�^���܂܂��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B<BR>
     * <BR>
     * �R�j�Q�j�̌������ʂ�ԋp����B<BR>
     * @@param l_mainAccount-(�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return CommCampaignAccHistoryParams[]
     * @@throws WEB3BaseException
     */
	public CommCampaignAccHistoryParams[] getCommCampaignAccHistoryParams(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
	{
        final String STR_METHOD_NAME = 
            "getCommCampaignAccHistoryParams(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);
        
		// �p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }
        
        //�P�j��������������&���������f�[�^�R���e�i �ȉ��̌���������\���A���������������
        //�@@  ArrayList(���������f�[�^�R���e�i)���쐬����B
        String l_strQueryCondition = "account_id = ? and " +
        							 "appli_start_date <= ? and " +
                                     "appli_end_date >= ? and "+
                                     "valid_div = ?";

        
        String l_strSortCondition = " appli_start_date asc " ;
        
        List l_lisWhere = new ArrayList();
        l_lisWhere.add(String.valueOf(l_mainAccount.getAccountId()));
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        l_lisWhere.add(l_bizDate.roll(2)); 
        l_lisWhere.add(l_datBizDate);
        l_lisWhere.add(WEB3ValidDivDef.EFFECTIVE);

        // �Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        List l_lisRecords = null;
        try 
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
            	CommCampaignAccHistoryRow.TYPE,
            	l_strQueryCondition,
            	l_strSortCondition,
            	null,
            	l_lisWhere.toArray());
        }
        catch (DataFindException l_ex)
        {
            log.debug("�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return null;
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
        //�@@�Q�|�P�j�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B 
        if (l_lisRecords == null || l_lisRecords.isEmpty())  
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        // �Q�|�Q�j�擾����List���Ɂy�o�^�^�C�v="�Q�F�����ʌڋq�w��"�z�̃f�[�^���܂܂��ꍇ��null��ԋp����B
        for (int i = 0; i < l_lisRecords.size(); i++)
		{
        	if(WEB3AccInfoRegistTypeDef.FORCE_INDIVIDUAL.equals(
        			((CommCampaignAccHistoryParams)l_lisRecords.get(i)).getRegistType()))
        	{
        		return null;
        	}
        }
        
        // �R�j�Q�j�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        CommCampaignAccHistoryParams[] l_CommCampaignAccHistoryParams= new CommCampaignAccHistoryParams[l_lisRecords.size()];
        l_lisRecords.toArray(l_CommCampaignAccHistoryParams);        
        return l_CommCampaignAccHistoryParams;
	}
	
	/**
	 * (get���i�R�[�h)<BR>
	 * �萔�������L�����y�[�����i�}�X�^���珤�i�R�[�h���擾����B<BR>
	 * <BR>
	 * �P�j�萔�������L�����y�[�����i�}�X�^���烌�R�[�h���擾<BR>
	 * <BR>
	 * [��������]<BR>
	 *   �L�����y�[���萔������ID = ����.�L�����y�[���萔������ID<BR>
	 *   <BR>
	 * �Q�j�擾����List����ALoop�����ɂď��i�R�[�h��List�𐶐�<BR>
	 * <BR>
	 * �R�j�Q�j�Ő����������i�R�[�hList��String�^�̔z��ɕϊ����A�ԋp<BR>
	 * @@param l_lngAccInfoConditionId-(�L�����y�[���萔������ID)<BR>
	 * @@return String[]
	 * @@throws WEB3BaseException
	 */
	public String[] getCommProductCodes(long l_lngAccInfoConditionId) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getCommProductCodes(long l_lngAccInfoConditionId)";
		log.entering(STR_METHOD_NAME);
		// �P�j�萔�������L�����y�[�����i�}�X�^���烌�R�[�h���擾
		String l_strQueryCondition = "campaign_id = ?";
		Object[] l_objValue = { String.valueOf(l_lngAccInfoConditionId) };

		List l_lisRecords = null;
		List l_lisProductCodes = new ArrayList();
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisRecords = l_queryProcessor.doFindAllQuery(
				CommCampaignProductMstRow.TYPE,
				l_strQueryCondition,
				l_objValue);
		}
		catch (DataFindException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			return null;
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
					this.getClass().getName()
					+ STR_METHOD_NAME, 
					l_ex.getMessage(), 
					l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
					this.getClass().getName()
					+ STR_METHOD_NAME, 
					l_ex.getMessage(), 
					l_ex);
		}
 
        if (l_lisRecords == null || l_lisRecords.isEmpty())  
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
					this.getClass().getName()
					+ STR_METHOD_NAME);
        }

		// �Q�j�擾����List����ALoop�����ɂď��i�R�[�h��List�𐶐�
		for (int i = 0; i < l_lisRecords.size(); i++)
		{
			l_lisProductCodes.add(((CommCampaignProductMstParams)l_lisRecords.get(i)).getCommProductCode());
		}
		// �R�j�Q�j�Ő����������i�R�[�hList��String�^�̔z��ɕϊ����A�ԋp
		log.exiting(STR_METHOD_NAME);
		return (String[])l_lisProductCodes.toArray(new String[l_lisProductCodes.size()]);
	}

    /**
     * (is���o�C����p�����J��)<BR>
     * ���o�C����p�����̊J�݁^���J�݂𔻕ʂ���B  <BR>
     * <BR>
     * ���o�C����p�����J�݂̏ꍇtrue�A�ȊO(���o�C����p�������J��)�̏ꍇfalse��ԋp����B <BR>
     * <BR>
     * �P�j�@@�ڋq.getDateSouceObject()�ɂČڋq�s���擾�B <BR>
     * <BR>
     * �Q�j�@@�ڋq�s.���o�C����p�����J�݋敪 == "1�F�J��"�̏ꍇ�Atrue <BR>
     * �@@�@@��L�ȊO�inull���܂ށj�̏ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * @@param l_mainAccount-(�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isOnlyMobileOpen(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isOnlyMobileOpen(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        // �p�����[�^.�ڋq���`�F�b�N����B
        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^.�ڋqNull�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^.�ڋqNull�o���Ȃ��B");
        }

        //�ڋq.getDateSouceObject()�ɂČڋq�s���擾�B
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�ڋq�s.���o�C����p�����J�݋敪 == "1�F�J��"�̏ꍇ�Atrue
        if (WEB3OnlyMobileOpenDivDef.OPEN.equals(l_mainAccountRow.getOnlyMobileOpenDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //��L�ȊO�inull���܂ށj�̏ꍇ�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is���ʌ�t�����11�����o��)<BR>
     * ���ʌ�t�����11�����o�߂̌ڋq�����ʂ���B<BR>
     * <BR>
     * ���ʌ�t�����11�����o�߂̌ڋq�̏ꍇtrue�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get���ʌ�t�Ǘ�Params���R�[������B<BR>
     * <BR>
     * �@@[get���ʌ�t�Ǘ�Params�ɃZ�b�g����p�����[�^]<BR>
     * �@@�ڋq�F�@@�ڋq�I�u�W�F�N�g<BR>
     * <BR>
     * �Q�j�@@get���ʌ�t�Ǘ�Params()�̖߂�l��null�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �R�j�@@get���ʌ�t�Ǘ�Params()�̖߂�l�̗v�f����LOOP�������s���A<BR>
     * �@@�@@�@@���ʌ�t�����11�����o�߂����ʂ���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@get���ʌ�t�Ǘ�Params()�̖߂�l.get(index).get���ʌ�t��()���擾����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�擾�������ʌ�t���̂P�P������̓��t���Z�o����B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�R�|�Q�j�ɂĎZ�o�������t <= ���ݓ��t �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�݂Ȃ���t�����11�����o�߂����ʂ���B<BR>
     * <BR>
     * �@@�@@�R�|�R�|�P�j�@@get���ʌ�t�Ǘ�Params()�̖߂�l.get(index).get�݂Ȃ���t��()���擾����B<BR>
     * <BR>
     * �@@�@@�R�|�R�|�Q�j�@@get�݂Ȃ���t��()�̖߂�l��null�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �@@�@@�R�|�R�|�R�j�@@�擾�����݂Ȃ���t���̂P�P������̓��t���Z�o����B<BR>
     * <BR>
     * �@@�@@�R�|�R�|�S�j�@@�R�|�R�|�R�j�ɂĎZ�o�������t <= ���ݓ��t �̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �S�j��L�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isDeliveryDate(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDeliveryDate(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //this.get���ʌ�t�Ǘ�Params���R�[������B
        List l_lisDocManagementParams = this.getDocDeliveryManagementParams(l_mainAccount);

        //get���ʌ�t�Ǘ�Params()�̖߂�l��null�̏ꍇ�Afalse��ԋp����
        if (l_lisDocManagementParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        DocDeliveryManagementRow l_docManagementRow = null;
        //get���ʌ�t�Ǘ�Params()�̖߂�l�̗v�f����LOOP�������s��,���ʌ�t�����11�����o�߂����ʂ���B
        for (int i = 0; i < l_lisDocManagementParams.size(); i++)
        {
            //get���ʌ�t�Ǘ�Params()�̖߂�l.get(index).get���ʌ�t��()���擾����B
            l_docManagementRow = (DocDeliveryManagementRow)l_lisDocManagementParams.get(i);

            //�R�|�Q�j�擾�������ʌ�t���̂P�P������̓��t���Z�o����B
            Date l_datDeliveryDate =
                WEB3DateUtility.addMonth(l_docManagementRow.getDeliveryDate(), 11);

            //���ݓ��t
            Date l_datSystemTime =
                WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());

            //�@@�R�|�R�j�@@�R�|�Q�j�ɂĎZ�o�������t <= ���ݓ��t �̏ꍇ�A�݂Ȃ���t�����11�����o�߂����ʂ���B
            if (WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datSystemTime) <= 0)
            {
                //�R�|�R�|�P�j�@@get���ʌ�t�Ǘ�Params()�̖߂�l.get(index).get�݂Ȃ���t��()���擾����B
                Date l_datDeemedDeliveryDate =
                    l_docManagementRow.getDeemedDeliveryDate();
                //�R�|�R�|�Q�j�@@get�݂Ȃ���t��()�̖߂�l��null�̏ꍇ�Atrue��ԋp����B
                if (l_datDeemedDeliveryDate == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
                //�R�|�R�|�R�j�擾�����݂Ȃ���t���̂P�P������̓��t���Z�o����B
                l_datDeemedDeliveryDate =
                    WEB3DateUtility.addMonth(l_datDeemedDeliveryDate, 11);
                //�R�|�R�|�S�j�@@�R�|�R�|�R�j�ɂĎZ�o�������t <= ���ݓ��t �̏ꍇ�Atrue��ԋp����B
                if (WEB3DateUtility.compareToDay(l_datDeemedDeliveryDate, l_datSystemTime) <= 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
        }
        //��L�ȊO�̏ꍇ�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get���ʌ�t�Ǘ�Params)<BR>
     * ���������ɊY�����鏑�ʌ�t�Ǘ����R�[�h��List�^�ŕԋp����B<BR>
     * <BR>
     * �P�j�@@�d�q�������R�[�h�Ǘ��e�[�u������d�q�������R�[�h���擾����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�،���ЃR�[�h = ����.�ڋq.�،���ЃR�[�h and<BR>
     * �@@���X�R�[�h = ����.�ڋq.���X�R�[�h and <BR>
     * �@@���ʋ敪 = '010' and <BR>
     * �@@�L���敪 = '0'(valid) <BR>
     * <BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B<BR>
     * <BR>
     * �Q�j���ʌ�t�Ǘ����R�[�h���擾����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�����h�c = �ڋq.getAccountId() and<BR>
     * �@@���ʋ敪 = '010' and<BR>
     * �@@���P�j�̖߂�l��1���̏ꍇ��<BR>
     * �@@  �����R�[�h = �P�j�̖߂�l.get�d�q�������R�[�h()<BR>
     * �@@���P�j�̖߂�l���������̏ꍇ��<BR>
     *   �@@�����R�[�h in ( �P�j�̖߂�l.get�d�q�������R�[�h() )<BR>
     * <BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B<BR>
     * <BR>
     * �R�j�@@�Q�j�̌������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getDocDeliveryManagementParams(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocDeliveryManagementParams(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�d�q�������R�[�h�Ǘ��e�[�u������d�q�������R�[�h���擾����B
        //[��������]
        //�،���ЃR�[�h = ����.�ڋq.�،���ЃR�[�h and
        //���X�R�[�h = ����.�ڋq.���X�R�[�h and
        //���ʋ敪 = '010' and
        //�L���敪 = '0'(valid)
        String l_strQueryString =
            " institution_code = ? and branch_code = ? and document_div = ? and valid_flag = ? ";

        Object[] l_queryContainers = new Object[4];
        l_queryContainers[0] = l_mainAccount.getInstitution().getInstitutionCode();
        l_queryContainers[1] = l_mainAccount.getBranch().getBranchCode();
        l_queryContainers[2] = WEB3CategCodeDef.DOCUMENT_DELIVERY;
        l_queryContainers[3] = WEB3ValidFlagDef.VALID;

        List l_lisBatoProductManagements = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBatoProductManagements = l_queryProcessor.doFindAllQuery(
                BatoProductManagementRow.TYPE,
                l_strQueryString,
                l_queryContainers);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����
        if (l_lisBatoProductManagements == null || l_lisBatoProductManagements.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j���ʌ�t�Ǘ����R�[�h���擾����B
        //[��������]
        //�����h�c = �ڋq.getAccountId() and
        //���ʋ敪 = '010' and
        //���P�j�̖߂�l��1���̏ꍇ��
        //�����R�[�h = �P�j�̖߂�l.get�d�q�������R�[�h()
        //���P�j�̖߂�l���������̏ꍇ��
        //�����R�[�h in ( �P�j�̖߂�l.get�d�q�������R�[�h() )
        //���������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B
        StringBuffer l_sbDocQueryString = new StringBuffer();
        Object[] l_docQueryContainers = null;
        BatoProductManagementRow l_batoProductManagementRow = null;

        //���P�j�̖߂�l��1���̏ꍇ��
        if (l_lisBatoProductManagements.size() == 1)
        {
            l_sbDocQueryString.append(" account_id = ? and document_div = ? and product_code = ? ");
            l_batoProductManagementRow = (BatoProductManagementRow)l_lisBatoProductManagements.get(0);
            l_docQueryContainers = new Object[3];
            //��������
            l_docQueryContainers[0] = String.valueOf(l_mainAccount.getAccountId());
            l_docQueryContainers[1] = WEB3CategCodeDef.DOCUMENT_DELIVERY;
            l_docQueryContainers[2] = l_batoProductManagementRow.getBatoProductCode();
        }
        //���P�j�̖߂�l���������̏ꍇ��
        else
        {
            l_sbDocQueryString.append(" account_id = ? and document_div = ? and product_code in ( ?");
            for (int i = 1; i < l_lisBatoProductManagements.size(); i++)
            {
                l_sbDocQueryString.append(", ?");
            }
            l_sbDocQueryString.append(" )");

            List l_lisQueryContainers = new ArrayList();
            l_lisQueryContainers.add(String.valueOf(l_mainAccount.getAccountId()));
            l_lisQueryContainers.add(WEB3CategCodeDef.DOCUMENT_DELIVERY);
            //��������
            for (int i = 0;i < l_lisBatoProductManagements.size(); i++)
            {
                l_batoProductManagementRow = (BatoProductManagementRow)l_lisBatoProductManagements.get(i);
                l_lisQueryContainers.add(l_batoProductManagementRow.getBatoProductCode());
            }
            l_docQueryContainers = l_lisQueryContainers.toArray();
        }

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                DocDeliveryManagementRow.TYPE,
                l_sbDocQueryString.toString(),
                l_docQueryContainers);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (get�s����������)<BR>
     * �Y���ڋq�̕s���������󋵂�Ԃ��B<BR>
     * <BR>
     * �P�j�@@���������Ǘ��T�[�r�XImpl.get�s���������󋵂��R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����l��ԋp����B<BR>
     * <BR>
     * �� �ԋp�����l�̈ꗗ<BR>
     * "0"�F�@@�s����������<BR>
     * "1"�F�@@�s���������i�M�p�������J�݁j<BR>
     * "2"�F�@@�s���������i�M�p�����J�݁j<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getShortfallGenerationStatus(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getShortfallGenerationStatus(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //���������Ǘ��T�[�r�XImpl.get�s���������󋵂��R�[������B
        WEB3TPPaymentRequisitionManageService l_manageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(
                WEB3TPPaymentRequisitionManageService.class);
        String l_strShortfallGenerationStatus =
            l_manageService.getLackCashOccurSituation(l_mainAccount);

        //�P�j�Ŏ擾�����l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strShortfallGenerationStatus;
    }

    /**
     * (get�Ǐؔ�����)<BR>
     * �Y���ڋq�̒Ǐؔ����󋵂�Ԃ��B<BR>
     * <BR>
     * �P�j�@@���������Ǘ��T�[�r�XImpl.get�Ǐؔ����󋵂��R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����l��ԋp����B<BR>
     * <BR>
     * �� �ԋp�����l�̈ꗗ<BR>
     * "0"�F�@@�Ǐؖ�����<BR>
     * "1"�F�@@��ꐅ���Ǐؔ���<BR>
     * "2"�F�@@��񐅏��Ǐؔ���<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdddepositGenerationStatus(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdddepositGenerationStatus(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //���������Ǘ��T�[�r�XImpl.get�Ǐؔ����󋵂��R�[������
        WEB3TPPaymentRequisitionManageService l_manageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(
                WEB3TPPaymentRequisitionManageService.class);
        String l_strAdddepositGenerationStatus =
            l_manageService.getAdditionalMarginSituation(l_mainAccount);

        //�P�j�Ŏ擾�����l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strAdddepositGenerationStatus;
    }

    /**
     * (get�s�����������)<BR>
     * �s������������Ԃ��B<BR>
     * <BR>
     * �P�j�@@���������Ǘ��T�[�r�XImpl.get�s�������������R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����l��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return WEB3TPShortfallOccurInfo
     * @@throws WEB3BaseException
     */
    public WEB3TPShortfallOccurInfo getShortfallGenerationInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getShortfallGenerationInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //���������Ǘ��T�[�r�XImpl.get�s�������������R�[������
        WEB3TPPaymentRequisitionManageService l_manageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(
                WEB3TPPaymentRequisitionManageService.class);
        WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
            l_manageService.getShortfallGenerationInfo(l_mainAccount);

        //�P�j�Ŏ擾�����l��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_shortfallGenerationInfo;
    }

    /**
     * (get�Ǐؔ������)<BR>
     * �Ǐؔ�������Ԃ��B<BR>
     * <BR>
     * �P�j�@@���������Ǘ��T�[�r�XImpl.get�Ǐؔ��������R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����l��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@throws WEB3BaseException
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdddepositGenerationInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //���������Ǘ��T�[�r�XImpl.get�Ǐؔ��������R�[������
        WEB3TPPaymentRequisitionManageService l_manageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(
                WEB3TPPaymentRequisitionManageService.class);
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
            l_manageService.getAdddepositGenerationInfo(l_mainAccount);

        //�P�j�Ŏ擾�����l��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_adddepositGenerationInfo;
    }

    /**
     * (get�]�͎����~�敪)<BR>
     * �ڋq�]�͏����e�[�u���̎����~�敪���擾����B<BR>
     * <BR>
     * �P�j�@@this.get�ڋq�]�͏���Params���R�[������B<BR>
     * �@@[get�ڋq�]�͏���Params�ɃZ�b�g����p�����[�^]<BR>
     * �@@����ID�F�@@�ڋq�I�u�W�F�N�g.����ID<BR>
     * <BR>
     * �Q�j�@@this.get�ڋq�]�͏���Params�̖߂�l��null�o�Ȃ��ꍇ�A<BR>
     * this.get�ڋq�]�͏���Params�̖߂�l.get�����~�敪�Ŏ擾�����l��ԋp����B<BR>
     * <BR>
     * �R�j�@@this.get�ڋq�]�͏���Params�̖߂�l��null�̏ꍇ�A"0"��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getTPTradingStop(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTPTradingStop(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //this.get�ڋq�]�͏���Params���R�[������
        //[get�ڋq�]�͏���Params�ɃZ�b�g����p�����[�^]
        // ����ID�F�@@�ڋq�I�u�W�F�N�g.����ID
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            this.getTradingpowerCalcConditionParams(
                new Long(l_mainAccount.getAccountId()));

        if (l_tradingpowerCalcConditionParams != null)
        {
            //this.get�ڋq�]�͏���Params�̖߂�l��null�o�Ȃ��ꍇ
            //this.get�ڋq�]�͏���Params�̖߂�l.get�����~�敪�Ŏ擾�����l��ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tradingpowerCalcConditionParams.getTradingStop();
        }
        else
        {
            //this.get�ڋq�]�͏���Params�̖߂�l��null�̏ꍇ�A"0"��ԋp����
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.ENABLE;
        }
    }

    /**
     * (get�ڋq�]�͏���Params)<BR>
     * ���������ɊY������ڋq�]�͏������R�[�h��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�]�͏����e�[�u������ڋq�]�͏������R�[�h���擾����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@����ID = ����.����ID<BR>
     * <BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_accountId - (����ID)<BR>
     * ����ID<BR>
     * @@return TradingpowerCalcConditionParams
     * @@throws WEB3BaseException
     */
    public TradingpowerCalcConditionParams getTradingpowerCalcConditionParams(Long l_accountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradingpowerCalcConditionParams(Long)";
        log.entering(STR_METHOD_NAME);

        //�ڋq�]�͏����e�[�u������ڋq�]�͏������R�[�h���擾����
        //[��������]
        // ����ID = ����.����ID
        String l_strSql = " account_id = ? ";
        Object[] l_sqlValues = new Object[]{l_accountId};
        List l_lisRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingpowerCalcConditionRow.TYPE,
                l_strSql,
                l_sqlValues);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����
        if (l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        TradingpowerCalcConditionRow l_tradingpowerCalcConditionRow =
            (TradingpowerCalcConditionRow)l_lisRecords.get(0);
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            new TradingpowerCalcConditionParams(l_tradingpowerCalcConditionRow);

        //�P�j�Ŏ擾�������ʂ�ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_tradingpowerCalcConditionParams;
    }

    /**
     * (get�Ǐؖ������敪)<BR>
     * �ڋq�]�͏����e�[�u���̒Ǐؖ������敪���擾����B<BR>
     * <BR>
     * �P�j�@@this.get�ڋq�]�͏���Params���R�[������B<BR>
     * �@@[get�ڋq�]�͏���Params�ɃZ�b�g����p�����[�^]<BR>
     * �@@����ID�F�@@�ڋq�I�u�W�F�N�g.����ID<BR>
     * <BR>
     * �Q�j�@@this.get�ڋq�]�͏���Params�̖߂�l��null�o�Ȃ��ꍇ�A<BR>
     * this.get�ڋq�]�͏���Params�̖߂�l.get�Ǐؖ������敪�Ŏ擾�����l��ԋp����B<BR>
     * <BR>
     * �R�j�@@this.get�ڋq�]�͏���Params�̖߂�l��null�̏ꍇ�A"0"��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdditionalDepositStop(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdditionalDepositStop(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //this.get�ڋq�]�͏���Params���R�[������
        //[get�ڋq�]�͏���Params�ɃZ�b�g����p�����[�^]
        // ����ID�F�@@�ڋq�I�u�W�F�N�g.����ID
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            this.getTradingpowerCalcConditionParams(
                new Long(l_mainAccount.getAccountId()));

        if (l_tradingpowerCalcConditionParams != null)
        {
            //this.get�ڋq�]�͏���Params�̖߂�l��null�o�Ȃ��ꍇ
            //this.get�ڋq�]�͏���Params�̖߂�l.get�Ǐؖ������敪�Ŏ擾�����l��ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tradingpowerCalcConditionParams.getAdditionalDepositStop();
        }
        else
        {
            //this.get�ڋq�]�͏���Params�̖߂�l��null�̏ꍇ�A"0"��ԋp����
            log.exiting(STR_METHOD_NAME);
            return WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_NOT_STOP;
        }
    }
}@
