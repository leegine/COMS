head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : ���v���׏Ɖ�T�[�r�XImpl(WEB3PLSProfitLossSpecsServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���q (���u) �V�K�쐬
                 : 2006/10/19  ��іQ (���u) ���f��056,058,059,060,061,062,063
                 : 2006/11/07  ���� (���u) ���f��066
                 : 2006/11/07  ���� (���u) ���f��067
                 : 2006/12/15  �����q (���u) ���f��068
                 : 2006/12/18  �����q (���u) ���f��069
*/

package webbroker3.tradehistory.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3CommodityCodeTypeDef;
import webbroker3.common.define.WEB3ProfitLossRecordDivDef;
import webbroker3.common.define.WEB3ReturnDivDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradehistory.WEB3HistoryTradeHistoryDataManager;
import webbroker3.tradehistory.WEB3PLSProfitLossSpecsCsv;
import webbroker3.tradehistory.data.ProfitLossSpecParams;
import webbroker3.tradehistory.data.ProfitLossSpecRow;
import webbroker3.tradehistory.define.WEB3PLSProfitLossSpecsSortKeyItemDef;
import webbroker3.tradehistory.define.WEB3PlsBvsCarryoverBalanceRecDef;
import webbroker3.tradehistory.define.WEB3PlsBvsDetailOrderRecDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryProductDivDef;
import webbroker3.tradehistory.define.WEB3TradeHistorySortDivDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryTransactionDivDef;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadResponse;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsResponse;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsSortKeyUnit;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsUnit;
import webbroker3.tradehistory.service.delegate.WEB3PLSProfitLossSpecsService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���v���׏Ɖ�T�[�r�XImpl)<BR>
 * ���v���׏Ɖ�T�[�r�X�����N���X<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsServiceImpl extends WEB3ClientRequestService implements WEB3PLSProfitLossSpecsService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PLSProfitLossSpecsServiceImpl.class);

    /**
     * @@roseuid 418877BB005D
     */
    public WEB3PLSProfitLossSpecsServiceImpl()
    {

    }

    /**
     * ���v���׏Ɖ�����s���B<BR>
     * <BR>
     * �P�j���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �@@�� �����̃��N�G�X�g�f�[�^���A�w���v���׏Ɖ�N�G�X�g�x�̏ꍇ<BR>
     * �@@�@@�Ethis.get���v���׏Ɖ�()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�� �����̃��N�G�X�g�f�[�^���A�w���v���׃t�@@�C���_�E�����[�h���N�G�X�g�x�̏ꍇ<BR>
     * �@@�@@�Ethis.get���v���׃t�@@�C���_�E�����[�h()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 416CFD660297
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3PLSProfitLossSpecsRequest)
        {
            l_response = this.getProfitLossSpecs((WEB3PLSProfitLossSpecsRequest)l_request);
        }
        else if (l_request instanceof WEB3PLSProfitLossDownloadRequest)
        {
            l_response = this.getProfitLossDownload((WEB3PLSProfitLossDownloadRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���v���׏Ɖ�)<BR>
     * ���v���׏Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���v���׏Ɖ�T�[�r�X)get���v���׏Ɖ�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���v���׏Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.plsbvs.message.WEB3PLSProfitLossSpecsResponse
     * @@roseuid 416CFDB50110
     */
    protected WEB3PLSProfitLossSpecsResponse getProfitLossSpecs(
        WEB3PLSProfitLossSpecsRequest l_request) throws WEB3BaseException
    {
       final String STR_METHOD_NAME = "getProfitLossSpecs(WEB3PLSProfitLossSpecsRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();

        //1.2 validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3 get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.4 getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.5 get���v���׌���(�ڋq, String)
        //�ڋq�F�@@getMainAccount()�̖߂�l
        //�����敪�F�@@���N�G�X�g�f�[�^.�����敪
        WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager = new WEB3HistoryTradeHistoryDataManager();
        int l_intProfitLossSpecCount =
            l_historyTradeHistoryDataManager.getProfitLossSpecCount(
                l_mainAccount,
                l_request.transactionDiv);

        WEB3PLSProfitLossSpecsResponse l_response =
            (WEB3PLSProfitLossSpecsResponse)l_request.createResponse();
        if (l_intProfitLossSpecCount == 0)
        {
            l_response.totalPages = "1";
            l_response.totalRecords = "0";
            l_response.pageIndex = "1";
            l_response.profitLossUnits = null;
            return l_response;
        }

        //1.6 �擾�f�[�^�i�[�pArrayList�𐶐�����B
        List l_lisProfitLossSpecs = new ArrayList();

        //1.7 create�c���f�[�^(�ڋq)

        WEB3PLSProfitLossSpecsUnit l_web3PLSProfitLossSpecsUnit = this.createBalanceData(l_mainAccount);
        //1.8 create�c���f�[�^()�̖߂�l == null�̏ꍇ
        if (l_web3PLSProfitLossSpecsUnit == null)
        {
            //1.8.1 createResponse()

            //1.8.2 �v���p�e�B�Z�b�g
            l_response.totalPages  = "1";
            l_response.totalRecords  = "0";
            l_response.pageIndex  = "1";
            l_response.profitLossUnits  = null;
            return l_response;
        }

        //1.9(*)���N�G�X�g�f�[�^.�����敪 == null(2�����\��) �̏ꍇ
        List l_lisprofitLossSpecList = null;
        if (l_request.transactionDiv == null)
        {
            //1.9.1 create�J�z�c���f�[�^(�ڋq, String, ���v���׏��)
            WEB3PLSProfitLossSpecsUnit l_plsProfitLossSpecsUnit =
               this.createCarryoverBalanceData(
                   l_mainAccount,
                   l_request.displayTerm,
                   l_web3PLSProfitLossSpecsUnit);

            //add(Object)
            //ArrayList�ɌJ�z�c���f�[�^��ǉ�����B
            //�J�z�c���f�[�^�F�@@create�J�z�c���f�[�^()�̖߂�l
            l_lisProfitLossSpecs.add(l_plsProfitLossSpecsUnit);

            //1.9.3 get���v���׈ꗗ(�ڋq, String, String, Date)
            l_lisprofitLossSpecList =
                l_historyTradeHistoryDataManager.getProfitLossSpecList(
                    l_mainAccount,
                    WEB3PlsBvsDetailOrderRecDef.DETAIL_ORDER_REC,
                    l_request.displayTerm,
                    null);
        }

        //1.10 (*)���N�G�X�g�f�[�^.�����敪 == 01(18�����\��) �̏ꍇ
        if (WEB3TradeHistoryTransactionDivDef.EIGHTEEN_MONTH_DISPLAY.equals(l_request.transactionDiv))
        {
            Date l_datListStartDate = WEB3DateUtility.getDate(l_request.listStartDate, "yyyyMMdd");
            Date l_datListEndDate = WEB3DateUtility.getDate(l_request.listEndDate, "yyyyMMdd");
            //1.10.1�i*�j�����\�����i���j���[����̑J�ځj�̏ꍇ
            //���N�G�X�g�f�[�^.�\������From == null & ���N�G�X�g�f�[�^.�\������To == null
            Date l_datCalcDate = null;
            if (l_request.listStartDate == null && l_request.listEndDate == null)
            {
                //1.10.1.1create��������������(Date, Date, String, String)
                //�\������From�F�@@���N�G�X�g�f�[�^.�\������From
                //�\������To�F�@@���N�G�X�g�f�[�^.�\������To
                //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
                //���i�敪�F�@@���N�G�X�g�f�[�^.���i�敪
                String l_strQueryString = this.createQueryString(
                    l_datListStartDate,
                    l_datListEndDate,
                    l_request.productCode,
                    l_request.commodityType);

                //1.10.1.2 create���������f�[�^�R���e�i(Date, Date, String, String)
                //�\������From�F�@@���N�G�X�g�f�[�^.�\������From
                //�\������To�F�@@���N�G�X�g�f�[�^.�\������To
                //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
                //���i�敪�F�@@���N�G�X�g�f�[�^.���i�敪
                String[] l_strQueryDataContainers = this.createQueryDataContainer(
                    l_datListStartDate,
                    l_datListEndDate,
                    l_request.productCode,
                    l_request.commodityType);

                //create�\�[�g����(String, ���v���׃\�[�g�L�[)
                //�\�[�g�敪�F�@@01�F�����\��
                //�\�[�g�L�[�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
                String l_strSortCond = this.createSortCond(
                    WEB3TradeHistorySortDivDef.INITIAL_DISPLAY,
                    l_request.sortKeys);

                //1.10.1.4get�v�Z�N����(�ڋq, String, Object[], String)
                //�ڋq�F�@@getMainAccount()�̖߂�l
                //��������������F�@@create�����󌟕�����()�̖߂�l
                //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l
                //�\�[�g�����F�@@create�\�[�g����()�̖߂�l
                l_datCalcDate = l_historyTradeHistoryDataManager.getCalcDate(
                    l_mainAccount,
                    l_strQueryString,
                    l_strQueryDataContainers,
                    l_strSortCond);
            }
            //1.10.2create��������������(Date, Date, String, String)
            //�\������From�F�@@���N�G�X�g�f�[�^.�\������From (*1)
            //�\������To�F�@@���N�G�X�g�f�[�^.�\������To (*1)
            //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
            //���i�敪�F�@@���N�G�X�g�f�[�^.���i�敪
            //(*1)
            //�\������From = null & �\������To = null�i�����\���j�̏ꍇ
            //�@@�E�\������From�F�@@get�v�Z�N����()�̖߂�l��1�����O�̓��t
            //�@@�E�\������To�F�@@get�v�Z�N����()�̖߂�l
            //��get�v�Z�N����()�̖߂�l = null �̏ꍇ�A�\������From��To�ɂ� null��ݒ肷��.
            if (l_request.listStartDate == null && l_request.listEndDate == null)
            {
                if (l_datCalcDate != null)
                {
                    Calendar l_calendar = new GregorianCalendar();
                    l_calendar.setTime(l_datCalcDate);
                    l_calendar.add(Calendar.MONTH, -1);
                    l_datListStartDate = l_calendar.getTime();
                    l_datListEndDate = l_datCalcDate;
                }
            }
            String l_strQueryString = this.createQueryString(
                    l_datListStartDate,
                    l_datListEndDate,
                    l_request.productCode,
                    l_request.commodityType);

            //1.10.3create���������f�[�^�R���e�i(Date, Date, String, String)
            String[] l_strQueryDataContainers = this.createQueryDataContainer(
                l_datListStartDate,
                l_datListEndDate,
                l_request.productCode,
                l_request.commodityType);

            //create�\�[�g����(String, ���v���׃\�[�g�L�[)
            //�\�[�g�敪�F�@@02�F�����\���ȊO
            //�\�[�g�L�[�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
            String l_strSortCond = this.createSortCond(
                WEB3TradeHistorySortDivDef.INITIAL_DISPLAY_OTHERS,
                l_request.sortKeys);

            //1.10.5get���v���׈ꗗ(�ڋq, String, Object[], String)
            //�ڋq�F�@@getMainAccount()�̖߂�l
            //��������������F�@@create�����󌟕�����()�̖߂�l
            //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l
            //�\�[�g�����F�@@create�\�[�g����()�̖߂�l
            l_lisprofitLossSpecList =
                l_historyTradeHistoryDataManager.getProfitLossSpecList(
                    l_mainAccount,
                    l_strQueryString,
                    l_strQueryDataContainers,
                    l_strSortCond);

            for (int i = 0; i < l_request.sortKeys.length; i++)
            {
                //���N�G�X�g�f�[�^.�\�[�g�L�[.�L�[���� = "calcDate(�v�Z�N����)"�@@���A
                //���N�G�X�g�f�[�^.�\�[�g�L�[.����/�~�� = D(�~��) �̏ꍇ
                //���N�G�X�g�f�[�^.�\�[�g�L�[�v�f������L�̏������s���B
                if (WEB3PLSProfitLossSpecsSortKeyItemDef.CALC_DATE.equals(l_request.sortKeys[i].keyItem)
                    && WEB3AscDescDef.DESC.equals(l_request.sortKeys[i].ascDesc))
                {
                    //add(Object)
                    //ArrayList�Ɏc���f�[�^��ǉ�����B
                    //�c���f�[�^�F�@@create�c���f�[�^()�̖߂�l
                    l_lisProfitLossSpecs.add(l_web3PLSProfitLossSpecsUnit);
                }
            }
        }

        int l_intSize = 0;
        if (l_lisprofitLossSpecList != null)
        {
            l_intSize = l_lisprofitLossSpecList.size();
        }

        //1.11 �擾��������&���o�����R�[�h(=���v����Params)����Loop����
        for (int i = 0; i < l_intSize; i++)
        {
             //1.11.1 ���v���׏��( )
             WEB3PLSProfitLossSpecsUnit l_profitLossSpecsUnit = new WEB3PLSProfitLossSpecsUnit();

             //1.11.2 ����t���[���׃��R�[�h�̏ꍇ(���v����Params.���R�[�h�敪 == "20�F���׃��R�[�h")
             ProfitLossSpecParams  l_profitLossSpecParams =  (ProfitLossSpecParams)l_lisprofitLossSpecList.get(i);
             if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecParams.getRecDiv()))
             {
                  //1.11.2.1 �v���p�e�B�Z�b�g
                  if (l_profitLossSpecParams.getProfitLossSpecIdIsSet())
                  {
                      l_profitLossSpecsUnit.profitLossSpecId = "" + l_profitLossSpecParams.getProfitLossSpecId();
                  }
                  else
                  {
                      l_profitLossSpecsUnit.profitLossSpecId = "";
                  }
                  l_profitLossSpecsUnit.prolossRecDiv = l_profitLossSpecParams.getRecDiv();
                  l_profitLossSpecsUnit.calcDate = l_profitLossSpecParams.getCalcDate();
                  l_profitLossSpecsUnit.deliveryDate = l_profitLossSpecParams.getDeliveryDate();
                  l_profitLossSpecsUnit.productName = l_profitLossSpecParams.getStandardName();
                  l_profitLossSpecsUnit.fundType = l_profitLossSpecParams.getCommodityDiv();
                  l_profitLossSpecsUnit.applicationCode = l_profitLossSpecParams.getApplicationCode();
                  l_profitLossSpecsUnit.termDiv = l_profitLossSpecParams.getTermDiv();
                  if (l_profitLossSpecParams.getQuantityIsNull())
                  {
                      l_profitLossSpecsUnit.quantity = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.quantity =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getQuantity()/100000);
                  }

                  l_profitLossSpecsUnit.passDate = l_profitLossSpecParams.getPassDate();

                  if (l_profitLossSpecParams.getPassAmountIsNull())
                  {
                      l_profitLossSpecsUnit.passAmount = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.passAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getPassAmount());
                  }

                  l_profitLossSpecsUnit.getDate = l_profitLossSpecParams.getGetDate();

                  if (l_profitLossSpecParams.getGetAmountIsNull())
                  {
                      l_profitLossSpecsUnit.getAmount = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.getAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getGetAmount());
                  }

                  if (l_profitLossSpecParams.getProlossAmountIsNull())
                  {
                      l_profitLossSpecsUnit.prolossAmount = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.prolossAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getProlossAmount());
                  }

                  if (l_profitLossSpecParams.getTotalProlossAmountIsNull())
                  {
                      l_profitLossSpecsUnit.totalProlossAmount = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.totalProlossAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getTotalProlossAmount());
                  }
             }
             //1.11.3 ����t���[���o�����R�[�h�̏ꍇ(���v����Params.���R�[�h�敪 == "21�F���o�����R�[�h")
             else if (l_profitLossSpecParams.getRecDiv().equals(WEB3ProfitLossRecordDivDef.ORDER_REC))
             {
                  //1.11.3.1 �v���p�e�B�Z�b�g
                  if (l_profitLossSpecParams.getProfitLossSpecIdIsSet())
                  {
                      l_profitLossSpecsUnit.profitLossSpecId = "" + l_profitLossSpecParams.getProfitLossSpecId();
                  }
                  else
                  {
                      l_profitLossSpecsUnit.profitLossSpecId = null;
                  }
                  l_profitLossSpecsUnit.prolossRecDiv = l_profitLossSpecParams.getRecDiv();
                  l_profitLossSpecsUnit.calcDate = l_profitLossSpecParams.getCalcDate();
                  l_profitLossSpecsUnit.deliveryDate = l_profitLossSpecParams.getDeliveryDate();
                  l_profitLossSpecsUnit.returnDiv = l_profitLossSpecParams.getReturnDiv();
                  l_profitLossSpecsUnit.remark = l_profitLossSpecParams.getRemark();

                  if (!l_profitLossSpecsUnit.returnDiv.equals(WEB3ReturnDivDef.RETURN))
                  {
                      if (l_profitLossSpecParams.getProlossAmountIsNull())
                      {
                          l_profitLossSpecsUnit.prolossAmount = null;
                      }
                      else
                      {
                          l_profitLossSpecsUnit.prolossAmount =
                              WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getProlossAmount());
                      }

                      if (l_profitLossSpecParams.getTaxableAmountIsNull())
                      {
                          l_profitLossSpecsUnit.taxableAmount = null;
                      }
                      else
                      {
                          l_profitLossSpecsUnit.taxableAmount =
                              WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getTaxableAmount());
                      }

                  }
                  if (l_profitLossSpecParams.getCollectTaxAmountIsNull())
                  {
                      l_profitLossSpecsUnit.collectTaxAmount = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.collectTaxAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getCollectTaxAmount());
                  }
                  if (l_profitLossSpecParams.getCollectTaxNAmountIsNull())
                  {

                  }
                  else
                  {
                      l_profitLossSpecsUnit.collectTaxNAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getCollectTaxNAmount());
                  }
                  if (l_profitLossSpecParams.getCollectTaxLAmountIsNull())
                  {

                  }
                  else
                  {
                      l_profitLossSpecsUnit.collectTaxLAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getCollectTaxLAmount());
                  }
             }
             //add(Object)
             //ArrayList�ɖ��ׂ܂��͓��o���f�[�^��ǉ�����B
             //����or���o���f�[�^�F�@@�v���p�e�B�Z�b�g�������v���׏��
             l_lisProfitLossSpecs.add(l_profitLossSpecsUnit);
        }

        //���N�G�X�g�f�[�^.�����敪 == null(2�����\��) �̏ꍇ
        if (l_request.transactionDiv == null)
        {
            //add(Object)
            //ArrayList�Ɏc���f�[�^��ǉ�����B
            //�c���f�[�^�F�@@create�c���f�[�^()�̖߂�l
            l_lisProfitLossSpecs.add(l_web3PLSProfitLossSpecsUnit);
        }

        //���N�G�X�g�f�[�^.�����敪 == 01(18�����\��)�̏ꍇ
        //���N�G�X�g�f�[�^.�\�[�g�L�[.�L�[���� = "calcDate(�v�Z�N����)"�@@���A
        //���N�G�X�g�f�[�^.�\�[�g�L�[.����/�~�� = A(����) �̏ꍇ
        //ArrayList�Ɏc���f�[�^��ǉ�����B
        //���N�G�X�g�f�[�^.�\�[�g�L�[�v�f������L�̏������s���B
        if (WEB3TradeHistoryTransactionDivDef.EIGHTEEN_MONTH_DISPLAY.equals(l_request.transactionDiv))
        {
            for (int i = 0; i < l_request.sortKeys.length; i++)
            {
                //add(Object)
                if (WEB3PLSProfitLossSpecsSortKeyItemDef.CALC_DATE.equals(l_request.sortKeys[i].keyItem)
                    && WEB3AscDescDef.ASC.equals(l_request.sortKeys[i].ascDesc))
                {
                    l_lisProfitLossSpecs.add(l_web3PLSProfitLossSpecsUnit);
                }
            }
        }

        //1.13 toArray( )
        WEB3PLSProfitLossSpecsUnit[] l_web3PLSProfitLossSpecsUnits =
            new WEB3PLSProfitLossSpecsUnit[l_lisProfitLossSpecs.size()];
        l_lisProfitLossSpecs.toArray(l_web3PLSProfitLossSpecsUnits);
        //1.14 createResponse
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_web3PLSProfitLossSpecsUnits, l_intPageIndex, l_intPageSize);
        WEB3PLSProfitLossSpecsUnit[] l_returnSpecUnit =
            (WEB3PLSProfitLossSpecsUnit[])l_pageIndexInfo.getArrayReturned(WEB3PLSProfitLossSpecsUnit.class);
        //1.15 (*)�v���p�e�B�Z�b�g
        l_response.totalPages  = l_pageIndexInfo.getTotalPages() + "";
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        l_response.profitLossUnits = l_returnSpecUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�c���f�[�^)<BR>
     * �c���f�[�^���i�[�������v���׏��C���X�^���X��ԋp����B<BR>
     * <BR>
     * �P�j��������ް��Ȱ�ެ.get�c�����R�[�hFrom���v����()���\�b�h��<BR>
     * �@@�R�[�����A���v����Params���擾����B<BR>
     * <BR>
     * �@@[get�c�����R�[�hFrom���v����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * <BR>
     * �@@�߂�l��null�̏ꍇ�Anull��ԋp���I������B<BR>
     * <BR>
     * �Q�j���v���׏��C���X�^���X�𐶐����ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@ID     ���@@�P�j�̖߂�l.���v����ID<BR>
     * �@@�@@��ƔN����  ���@@�P�j�̖߂�l.��ƔN����<BR>
     * �@@�@@���v���׃��R�[�h�敪�@@���@@�P�j�̖߂�l.���R�[�h�敪<BR>
     * �@@�@@���n���z   ���@@�P�j�̖߂�l.���n���z<BR>
     * �@@�@@�擾�   ���@@�P�j�̖߂�l.�擾�<BR>
     * �@@�@@�݌v���v   ���@@�P�j�̖߂�l.�݌v���v<BR>
     * <BR>
     * �@@[�@@��������ް��Ȱ�ެ.is�P�Q��() == true�̏ꍇ]<BR>
     * �@@�@@�����Ŋz   ���@@�P�j�̖߂�l.�����ϐŊz(����)<BR>
     * �@@�@@�����Ŋz(����)   ���@@�P�j�̖߂�l.�����ϐŊz(��������)<BR>
     * �@@�@@�����Ŋz(�n����)  ���@@�P�j�̖߂�l.�����ϐŊz(�����n����)<BR>
     * <BR>
     * �@@[�@@�ȊO�̏ꍇ]<BR>
     * �@@�@@�����Ŋz   ���@@�P�j�̖߂�l.�����ϐŊz(����)<BR>
     * �@@�@@�����Ŋz(����)   ���@@�P�j�̖߂�l.�����ϐŊz(��������)<BR>
     * �@@�@@�����Ŋz(�n����)  ���@@�P�j�̖߂�l.�����ϐŊz(�����n����)<BR>
     * <BR>
     * �@@�@@��is�P�Q��()�̃p�����[�^�ɂ́A�P�j�̖߂�l.��ƔN�������Z�b�g�B<BR>
     * <BR>
     * �R�j�v���p�e�B�Z�b�g�������v���׏���ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return webbroker3.plsbvs.message.WEB3PLSProfitLossSpecsUnit
     * @@roseuid 416E0E640322
     */
    protected WEB3PLSProfitLossSpecsUnit createBalanceData(WEB3GentradeMainAccount l_mainAccount)  throws  WEB3BaseException
    {
        final String STR_METHOD_NAME = "createBalanceData(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);

        //�P�j��������ް��Ȱ�ެ.get�c�����R�[�hFrom���v����()���\�b�h��
        // �R�[�����A���v����Params���擾����B
        WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager = new WEB3HistoryTradeHistoryDataManager();
        ProfitLossSpecParams l_profitLossSpecParams = l_historyTradeHistoryDataManager.getBalanceRecordFromProfitLoss(l_mainAccount);
        if (l_profitLossSpecParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //2 ���v���׏��C���X�^���X�𐶐����ȉ��̃v���p�e�B���Z�b�g����B
        WEB3PLSProfitLossSpecsUnit l_plsProfitLossSpecsUnit = new WEB3PLSProfitLossSpecsUnit();
        //ID ���@@�P�j�̖߂�l.���v����ID
        if (l_profitLossSpecParams.getProfitLossSpecIdIsSet())
        {
            l_plsProfitLossSpecsUnit.profitLossSpecId = "" + l_profitLossSpecParams.getProfitLossSpecId();
        }
        //��ƔN���� ���@@�P�j�̖߂�l.��ƔN����
        l_plsProfitLossSpecsUnit.workDate = l_profitLossSpecParams.getWorkDate();
        l_plsProfitLossSpecsUnit.prolossRecDiv = l_profitLossSpecParams.getRecDiv();
        if (l_profitLossSpecParams.getPassAmountIsNull())
        {
            l_plsProfitLossSpecsUnit.passAmount = null;
        }
        else
        {
            l_plsProfitLossSpecsUnit.passAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getPassAmount());
        }
         
        if (l_profitLossSpecParams.getGetAmountIsNull())
        {
            l_plsProfitLossSpecsUnit.getAmount = null;
        }
        else
        {
            l_plsProfitLossSpecsUnit.getAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getGetAmount());
        }
        
        if (l_profitLossSpecParams.getTotalProlossAmountIsNull())
        {
            l_plsProfitLossSpecsUnit.totalProlossAmount = null;
        }
        else
        {
            l_plsProfitLossSpecsUnit.totalProlossAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getTotalProlossAmount());
        }
        
        //[�@@��������ް��Ȱ�ެ.is�P�Q��() == true�̏ꍇ]
        if (l_historyTradeHistoryDataManager.isDecember(l_profitLossSpecParams.getWorkDate()))
        {
            if (l_profitLossSpecParams.getColltaxAmountCurrIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxAmountCurr());
            }
            
            if (l_profitLossSpecParams.getColltaxNAmountCurrIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxNAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxNAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxNAmountCurr());
            }
            
            if (l_profitLossSpecParams.getColltaxLAmountCurrIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxLAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxLAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxLAmountCurr());    
            }
            
        }
        else
        {
            if (l_profitLossSpecParams.getColltaxAmountNxtIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxAmountNxt());
            }
            if (l_profitLossSpecParams.getColltaxNAmountNxtIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxNAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxNAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxNAmountNxt());
            }
            if (l_profitLossSpecParams.getColltaxLAmountNxtIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxLAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxLAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxLAmountNxt());
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_plsProfitLossSpecsUnit;
    }

    /**
     * (create�J�z�c���f�[�^)<BR>
     * �J�z�c���f�[�^���i�[���鑹�v���׏���ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���v���׏Ɖ�T�[�r�X)create�J�z�c���f�[�^�v�Q��<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strDisplayTerm - (�\������)<BR>
     * �\������<BR>
     * <BR>
     * 0�F�@@�O�������ȍ~(DEFAULT)<BR>
     * 1�F�@@1������<BR>
     * 2�F�@@1�T�ԕ�<BR>
     * 3�F�@@�O��1����<BR>
     * @@param l_balanceUnit - (�c�����)<BR>
     * �c�������i�[�������v���׏��<BR>
     * @@return webbroker3.plsbvs.message.WEB3PLSProfitLossSpecsUnit
     * @@roseuid 416E201501DA
     */
    protected WEB3PLSProfitLossSpecsUnit createCarryoverBalanceData(WEB3GentradeMainAccount l_mainAccount, String l_strDisplayTerm, WEB3PLSProfitLossSpecsUnit l_balanceUnit) throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "createCarryoverBalanceData(WEB3GentradeMainAccount, String, WEB3PLSProfitLossSpecsUnit)";
         log.entering(STR_METHOD_NAME);

         // 1.1  get�݌v���v(�ڋq, String)
         WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager = new WEB3HistoryTradeHistoryDataManager();
         double  l_dblTotalProlossAmount = l_historyTradeHistoryDataManager.getTotalProlossAmount(l_mainAccount, l_strDisplayTerm);

         //1.2 get���v���׈ꗗ(�ڋq, String, String, Date)
         List  l_lstProfitLossSpec  = l_historyTradeHistoryDataManager.getProfitLossSpecList(l_mainAccount, WEB3PlsBvsDetailOrderRecDef.PAYMENT_REC, l_strDisplayTerm, l_balanceUnit.workDate);
         int l_listProfitLossSpecSize = 0;
         if (l_lstProfitLossSpec != null)
         {
             l_listProfitLossSpecSize = l_lstProfitLossSpec.size();
         }

         //1.3 �擾�������o�����R�[�h(=���v����Params)����Loop����
         double  l_dblTotalCollectTaxAmount = 0;  //�����Ŋz
         double  l_dblTotalCollectTaxNAmount = 0; //�����Ŋz(����)
         double  l_dblTotalCollectTaxLAmount = 0; //�����Ŋz(�n����)

         for (int i = 0; i < l_listProfitLossSpecSize; i++)
         {
              l_dblTotalCollectTaxAmount += ((ProfitLossSpecParams)l_lstProfitLossSpec.get(i)).getCollectTaxAmount();
              l_dblTotalCollectTaxNAmount += ((ProfitLossSpecParams)l_lstProfitLossSpec.get(i)).getCollectTaxNAmount();
              l_dblTotalCollectTaxLAmount += ((ProfitLossSpecParams)l_lstProfitLossSpec.get(i)).getCollectTaxLAmount();
         }

         //1.4 ���v���׏��( )
         WEB3PLSProfitLossSpecsUnit  l_web3PLSProfitLossSpecsUnit = new WEB3PLSProfitLossSpecsUnit();

         //1.5 �v���p�e�B�Z�b�g
         //���v���׃��R�[�h�敪   ���@@"00�F�J�z�c��"
         l_web3PLSProfitLossSpecsUnit.prolossRecDiv = WEB3PlsBvsCarryoverBalanceRecDef.CARRYOVER_BALANCE_REC;
         //�݌v���v         ���@@get�݌v���v()�̖߂�l
         l_web3PLSProfitLossSpecsUnit.totalProlossAmount = WEB3StringTypeUtility.formatNumber(l_dblTotalProlossAmount);
         //�����Ŋz         ���@@�p�����[�^.�c�����.�����Ŋz - ���o�����R�[�h�̒����Ŋz�̍��v
         l_web3PLSProfitLossSpecsUnit.collectTaxAmount = WEB3StringTypeUtility.formatNumber(Double.parseDouble(l_balanceUnit.collectTaxAmount) - l_dblTotalCollectTaxAmount);
         //�����Ŋz(����)     ���@@�p�����[�^.�c�����.�����Ŋz(����) - ���o�����R�[�h�̒����Ŋz(����)�̍��v
         l_web3PLSProfitLossSpecsUnit.collectTaxNAmount = WEB3StringTypeUtility.formatNumber(Double.parseDouble(l_balanceUnit.collectTaxNAmount) - l_dblTotalCollectTaxNAmount);
         //�����Ŋz(�n����)        ���@@�p�����[�^.�c�����.�����Ŋz(�n����) - ���o�����R�[�h�̒����Ŋz(�n����)�̍��v
         l_web3PLSProfitLossSpecsUnit.collectTaxLAmount = WEB3StringTypeUtility.formatNumber(Double.parseDouble(l_balanceUnit.collectTaxLAmount) - l_dblTotalCollectTaxLAmount);

         log.exiting(STR_METHOD_NAME);
         return l_web3PLSProfitLossSpecsUnit;
    }

    /**
     * (create��������������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j[�\������]����������������ɃZ�b�g����B<BR>
     * ���p�����[�^.�\������From != null & �p�����[�^.�\������To != null�̏ꍇ�Ɏ��{����<BR>
     * <BR>
     * �@@�@@�������������� += "and calc_date >= to_date(?, 'YYYYMMDD') " <BR>
     * �@@�@@�������������� += "and calc_date <= to_date(?, 'YYYYMMDD') "<BR>
     * <BR>
     * �Q�j[�����R�[�h]����������������ɃZ�b�g����B<BR>
     * ���p�����[�^.�����R�[�h != null�̏ꍇ�Ɏ��{���� <BR>
     * <BR>
     * �@@�@@�������������� += "and product_code = ? "  <BR>
     * <BR>
     * �R�j[���i�敪]����������������ɃZ�b�g����B<BR>
     * ���p�����[�^.���i�敪 != null�̏ꍇ�Ɏ��{���� <BR>
     * <BR>
     * �@@�p�����[�^.���i�敪���A<BR>
     * <BR>
     * �@@[�hA:�S���i�h�̏ꍇ]<BR>
     * �@@�@@�������������� += "and rec_div in (?, ?) "<BR>
     * <BR>
     * �@@[�hB:�����E�M�p�h�̏ꍇ]<BR>
     * �@@�@@�������������� += "and rec_div = ? "<BR>
     * �@@�@@�������������� += "and commodity_div in (?, ?, ?, ?) "<BR>
     * <BR>
     * �@@[�hC:�����h�̏ꍇ]<BR>
     * �@@�@@�������������� += "and rec_div = ? "<BR>
     * �@@�@@�������������� += "and commodity_div in (?, ?, ?) "<BR>
     * <BR>
     * �@@[�hD:�M�p�h�܂��́hL:���h�̏ꍇ]<BR>
     * �@@�@@�������������� += "and rec_div = ? " <BR>
     * �@@�@@�������������� += "and commodity_div = ? "<BR>
     * <BR>
     * �@@[�hH:���M�h�܂��́hK:�O�������h�̏ꍇ] <BR>
     * �@@�@@�������������� += "and rec_div = ? " <BR>
     * �@@�@@�������������� += "and commodity_div in (?, ?) "<BR>
     * <BR>
     * �@@[�hI:���o���h�̏ꍇ]<BR>
     * �@@�@@�������������� += "and rec_div = ? "<BR>
     * <BR>
     * �S�j�쐬�������������������ԋp����B<BR>
     * @@param l_datListStartDate - (�\������From)<BR>
     * �\������From<BR>
     * (�`���FYYYYMMDD)<BR>
     * @@param l_datListEndDate - (�\������To)<BR>
     * �\������To<BR>
     * (�`���FYYYYMMDD)<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strCommodityType - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String createQueryString(
        Date l_datListStartDate, Date l_datListEndDate,
        String l_strProductCode, String l_strCommodityType)
    {
        final String STR_METHOD_NAME = " createQueryString(Date, Date, String, String)";

        log.entering(STR_METHOD_NAME);

        StringBuffer l_strQueryString = new StringBuffer();

        //�P�j[�\������]����������������ɃZ�b�g����B
        //���p�����[�^.�\������From != null & �p�����[�^.�\������To != null�̏ꍇ�Ɏ��{����
        //
        //�@@�@@�������������� += "and calc_date >= to_date(?, 'YYYYMMDD') "
        //�@@�@@�������������� += "and calc_date <= to_date(?, 'YYYYMMDD') "

        if (l_datListStartDate != null && l_datListEndDate != null)
        {
            l_strQueryString.append("and calc_date >= to_date(?, 'YYYYMMDD') ");
            l_strQueryString.append("and calc_date <= to_date(?, 'YYYYMMDD') ");
        }

        //�Q�j[�����R�[�h]����������������ɃZ�b�g����.
        //���p�����[�^.�����R�[�h != null�̏ꍇ�Ɏ��{����
        //
        //�@@�@@�������������� += "and product_code = ? "
        if (l_strProductCode != null)
        {
            l_strQueryString.append("and product_code = ? ");
        }

        //�R�j[���i�敪]����������������ɃZ�b�g����B
        //���p�����[�^.���i�敪 != null�̏ꍇ�Ɏ��{����
        if (l_strCommodityType != null)
        {
            //   �@@�p�����[�^.���i�敪���A
            //   �@@[�hA:�S���i�h�̏ꍇ]
            //   �@@�@@�������������� += "and rec_div in (?, ?) "
            if (WEB3TradeHistoryProductDivDef.ALL_PRODUCT.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div in (?, ?) ");
            }

            //   �@@[�hB:�����E�M�p�h�̏ꍇ]
            //   �@@�@@�������������� += "and rec_div = ? "
            //   �@@�@@�������������� += "and commodity_div in (?, ?, ?, ?) "
            if (WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div = ? ");
                l_strQueryString.append("and commodity_div in (?, ?, ?, ?) ");
            }

            //   �@@[�hC:�����h�̏ꍇ]
            //   �@@�@@�������������� += "and rec_div = ? "
            //   �@@�@@�������������� += "and commodity_div in (?, ?, ?) "
            if (WEB3TradeHistoryProductDivDef.EQUITY.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div = ? ");
                l_strQueryString.append("and commodity_div in (?, ?, ?) ");
            }

            //    [�hD:�M�p�h�܂��́hL:���h�̏ꍇ]
            //   �@@�@@�������������� += "and rec_div = ? "
            //   �@@�@@�������������� += "and commodity_div = ? "
            if (WEB3TradeHistoryProductDivDef.MARGIN.equals(l_strCommodityType)
                || WEB3TradeHistoryProductDivDef.BOND.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div = ? ");
                l_strQueryString.append("and commodity_div = ? ");
            }

            //   �@@[�hH:���M�h�܂��́hK:�O�������h�̏ꍇ]
            //   �@@�@@�������������� += "and rec_div = ? "
            //   �@@�@@�������������� += "and commodity_div in (?, ?) "
            if (WEB3TradeHistoryProductDivDef.MUTUAL_FUND_RUITO.equals(l_strCommodityType)
                || WEB3TradeHistoryProductDivDef.FOREIGN.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div = ? ");
                l_strQueryString.append("and commodity_div in (?, ?) ");
            }

            //   �@@[�hI:���o���h�̏ꍇ]
            //   �@@�@@�������������� += "and rec_div = ? "
            if (WEB3TradeHistoryProductDivDef.AIO.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div = ? ");
            }
        }

        // �S�j�쐬�������������������ԋp����
        String l_strQueryStringReturn = l_strQueryString.toString();

        log.exiting(STR_METHOD_NAME);
        return l_strQueryStringReturn;
    }

    /**
     * (create���������f�[�^�R���e�i) <BR>
     * ���������������"?"�����ɃZ�b�g����p�����[�^���X�g(������z��)���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j[�\������]��ArrayList�ɒǉ�����B<BR>
     * ���p�����[�^.�\������From != null & �p�����[�^.�\������To != null�̏ꍇ�Ɏ��{���� <BR>
     * <BR>
     * �@@�E�p�����[�^.�\������From  <BR>
     * �@@�E�p�����[�^.�\������To  <BR>
     * <BR>
     * �R�j[�����R�[�h]��ArrayList�ɒǉ�����B  <BR>
     * ���p�����[�^.�����R�[�h != null�̏ꍇ�Ɏ��{���� <BR>
     * <BR>
     *  �E�p�����[�^.�����R�[�h <BR>
     * <BR>
     * �S�j[���i�敪]��ArrayList�ɒǉ�����B  <BR>
     * ���p�����[�^.���i�敪 != null�̏ꍇ�Ɏ��{���� <BR>
     * <BR>
     * �@@[�hA:�S���i�h�̏ꍇ]  <BR>
     * �@@�E"20:���׃��R�[�h" <BR>
     * �@@�E"21:���o�����R�[�h" <BR>
     * <BR>
     * �@@[�hB:�����E�M�p�h�̏ꍇ]  <BR>
     * �@@�E"20:���׃��R�[�h" <BR>
     * �@@�E"10:��������"  <BR>
     * �@@�E"11:�~�j������"  <BR>
     * �@@�E"12:�~�j������"  <BR>
     * �@@�E"15:�M�p���"  <BR>
     * <BR>
     * �@@[�hC:�����h�̏ꍇ]  <BR>
     * �@@�E"20:���׃��R�[�h" <BR>
     * �@@�E"10:��������"  <BR>
     * �@@�E"11:�~�j������"  <BR>
     * �@@�E"12:�~�j������"  <BR>
     * <BR>
     * �@@[�hD:�M�p�h�̏ꍇ]  <BR>
     * �@@�E"20:���׃��R�[�h" <BR>
     * �@@�E"15:�M�p���"  <BR>
     * <BR>
     * �@@[�hL:���h�̏ꍇ]  <BR>
     * �@@�E"20:���׃��R�[�h" <BR>
     * �@@�E"30:������"  <BR>
     * <BR>
     * �@@[�hH:���M�h�̏ꍇ]  <BR>
     * �@@�E"20:���׃��R�[�h" <BR>
     * �@@�E"20:���M���"  <BR>
     * �@@�E"21:���M���"  <BR>
     * <BR>
     * �@@[�hK:�O�������h�̏ꍇ]  <BR>
     * �@@�E"20:���׃��R�[�h" <BR>
     * �@@�E"40:�O������"  <BR>
     * �@@�E"42:�O������"  <BR>
     * <BR>
     * �@@[�hI:���o���h�̏ꍇ]  <BR>
     * �@@�E"21:���o�����R�[�h" <BR>
     * <BR>
     * �T�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_datListStartDate - (�\������From)<BR>
     * �\������From<BR>
     * (�`���FYYYYMMDD)<BR>
     * @@param l_datListEndDate - (�\������To)<BR>
     * �\������To<BR>
     * (�`���FYYYYMMDD)<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strCommodityType - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * @@return String[]
     * @@roseuid 413C30E102D8
     */
    protected String[] createQueryDataContainer(
        Date l_datListStartDate, Date l_datListEndDate,
        String l_strProductCode, String l_strCommodityType)
        {
            final String STR_METHOD_NAME = " createQueryString(Date, Date, String, String)";

            log.entering(STR_METHOD_NAME);

            //* �P�jArrayList�𐶐�����B<BR>
            List l_lisContainers = new ArrayList();

            //�Q�j[�\������]��ArrayList�ɒǉ�����.
            //���p�����[�^.�\������From != null & �p�����[�^.�\������To != null�̏ꍇ�Ɏ��{����
            //�@@�E�p�����[�^.�\������From
            //�@@�E�p�����[�^.�\������To
            if ((l_datListStartDate != null) && (l_datListEndDate != null))
            {
                l_lisContainers.add(WEB3DateUtility.formatDate(l_datListStartDate, "yyyyMMdd"));
                l_lisContainers.add(WEB3DateUtility.formatDate(l_datListEndDate, "yyyyMMdd"));
            }

            //�R�j[�����R�[�h]��ArrayList�ɒǉ�����
            //���p�����[�^.�����R�[�h != null�̏ꍇ�Ɏ��{����
            //�@@�E�p�����[�^.�����R�[�h
            if (l_strProductCode != null)
            {
                l_lisContainers.add(l_strProductCode);
            }

            //�S�j[���i�敪]��ArrayList�ɒǉ�����B
            //���p�����[�^.���i�敪 != null�̏ꍇ�Ɏ��{����
            if (l_strCommodityType != null)
            {
                //[�hA:�S���i�h�̏ꍇ]
                // �E"20:���׃��R�[�h"
                //�E"21:���o�����R�[�h"
                if (WEB3TradeHistoryProductDivDef.ALL_PRODUCT.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
                }

                // [�hB:�����E�M�p�h�̏ꍇ]
                // �E"20:���׃��R�[�h"
                //�E"10:��������"
                //�E"11:�~�j������"
                //�E"12:�~�j������"
                //�E"15:�M�p���"
                if (WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.JAPANESE_STOCK_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MINI_STOCK_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MINI_STOCK_CLAIM);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MARGIN);
                }

                //[�hC:�����h�̏ꍇ]
                //�E"20:���׃��R�[�h"
                //�E"10:��������"
                //�E"11:�~�j������"
                //�E"12:�~�j������"
                if (WEB3TradeHistoryProductDivDef.EQUITY.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.JAPANESE_STOCK_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MINI_STOCK_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MINI_STOCK_CLAIM);
                }

                //[�hD:�M�p�h�̏ꍇ]
                //�E"20:���׃��R�[�h"
                //�E"15:�M�p���"
                if (WEB3TradeHistoryProductDivDef.MARGIN.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MARGIN);
                }

                //[�hL:���h�̏ꍇ]
                // �E"20:���׃��R�[�h"
                //�E"30:������"
                if (WEB3TradeHistoryProductDivDef.BOND.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.JAPANESE_BOND_TRADE);
                }

                //[�hH:���M�h�̏ꍇ]
                //�E"20:���׃��R�[�h"
                //�E"20:���M���"
                //�E"21:���M���"
                if (WEB3TradeHistoryProductDivDef.MUTUAL_FUND_RUITO.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MUTUAL_FUND_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MUTUAL_FUND_TRADING);
                }

                //[�hK:�O�������h�̏ꍇ]
                //�@@�E"20:���׃��R�[�h"
                //�@@�E"40:�O������"
                //�@@�E"42:�O������"
                if (WEB3TradeHistoryProductDivDef.FOREIGN.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.FOREIGN_STOCK_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.FOREIGN_STOCK_CLAIM);
                }

                // [�hI:���o���h�̏ꍇ]
                // �E"21:���o�����R�[�h"
                if (WEB3TradeHistoryProductDivDef.AIO.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
                }
            }

            //�T�j��������ArrayList.toArray()�̖߂�l��ԋp����B
            String[] l_strQueryDataContainers = new String[l_lisContainers.size()];
            l_lisContainers.toArray(l_strQueryDataContainers);

            log.exiting(STR_METHOD_NAME);
            return l_strQueryDataContainers;
        }

    /**
     * (create�\�[�g����)<BR>
     * �\�[�g������������쐬����B<BR>
     * <BR>
     * �E�e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A�\�[�g����������iorder by��j���쐬����B<BR>
     * <BR>
     * �P�j �p�����[�^.�\�[�g�敪 = 01:�����\�� �̏ꍇ<BR>
     * <BR>
     * �@@�P�|�P�j �ȉ��̃\�[�g�������쐬����B<BR>
     * �@@�@@�@@�@@�E �v�Z�N�����F�@@�~��<BR>
     * <BR>
     * �@@�@@�@@�@@�\�[�g���� = "calc_date desc "<BR>
     * <BR>
     * �Q�j �p�����[�^.�\�[�g�敪 = 02:�����\���ȊO �̏ꍇ<BR>
     * �@@�E����.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A�\�[�g������������쐬����B<BR>
     * <BR>
     * �@@�Q�|�P�j �\�[�g�L�[��ҏW����B<BR>
     * �@@�@@�Q�|�P�|�P�j �L�[���� = �ucalcDate�F�v�Z�N�����v�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�\�[�g���� = "calc_date "<BR> 
     * <BR>
     * �@@�Q�|�Q�j �\�[�g������ҏW����B<BR>
     * �@@�@@�Q�|�Q�|�P�j �����^�~�� = �uA�F�����v�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�@@�\�[�g���� += "asc "<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j �����^�~�� = �uD�F�~���v�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�@@�\�[�g���� += "desc "<BR>
     * <BR>
     * �@@�Q�|�R�j �u���v���׃e�[�u��.SORT-NO�v���\�[�g�����ɒǉ�����B<BR>
     * �@@�@@�Q�|�R�|�P�j �����^�~�� = �uA�F�����v�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�@@�\�[�g���� += ", sort_no asc "<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j �����^�~�� = �uD�F�~���v�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�\�[�g���� += ", sort_no desc "<BR>
     * <BR>
     * <BR>
     * �R�j �p�����[�^.�\�[�g�敪 = 03�F�_�E�����[�h �̏ꍇ<BR>
     * <BR>
     * �@@�R�|�P�j �ȉ��̃\�[�g�������쐬����B<BR>
     * �@@�@@�@@�@@�E �v�Z�N�����F�@@����<BR>
     * �@@�@@�@@�@@�E SORT-NO�F�@@����<BR>
     * <BR>
     * �@@�@@�@@�@@�\�[�g���� = "calc_date asc, sort_no asc "<BR>
     * <BR>
     * <BR>
     * 4�j �쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_strSortDiv - (�\�[�g�敪)<BR>
     * �\�[�g�敪<BR>
     * �@@01�F�@@�����\��<BR>
     * �@@02�F�@@�����\���ȊO<BR>
     * �@@03�F�@@�_�E�����[�h<BR>
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * ���v���׃\�[�g�L�[�I�u�W�F�N�g
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String createSortCond(String l_strSortDiv,
        WEB3PLSProfitLossSpecsSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            " createSortCond(String l_strSortDiv, WEB3PLSProfitLossSpecsSortKeyUnit[] l_sortKey) ";

        log.entering(STR_METHOD_NAME);

        String l_strSort = "";

        //�P�j �p�����[�^.�\�[�g�敪 = 01:�����\�� �̏ꍇ
        if (WEB3TradeHistorySortDivDef.INITIAL_DISPLAY.equals(l_strSortDiv))
        {
            //�P�|�P�j �ȉ��̃\�[�g�������쐬����.
            //�E �v�Z�N�����F�@@�~��
            //�\�[�g���� = "calc_date desc "
            l_strSort = "calc_date desc ";
        }
        //�Q�j �p�����[�^.�\�[�g�敪 = 02:�����\���ȊO �̏ꍇ
        //�E����.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A�\�[�g������������쐬����B
        else if (WEB3TradeHistorySortDivDef.INITIAL_DISPLAY_OTHERS.equals(l_strSortDiv))
        {
            if (l_sortKeys != null || l_sortKeys.length !=0)
            {
                for (int i = 0; i < l_sortKeys.length; i++)
                {
                    //�Q�|�P�j �\�[�g�L�[��ҏW����B
                    //�Q�|�P�|�P�j �L�[���� = �ucalcDate�F�v�Z�N�����v�̏ꍇ�A
                    //�\�[�g���� = "calc_date "
                    if (WEB3PLSProfitLossSpecsSortKeyItemDef.CALC_DATE.equals(l_sortKeys[i].keyItem))
                    {
                        l_strSort = "calc_date ";
                    }

                    //�Q�|�Q�|�P�j �����^�~�� = �uA�F�����v�̏ꍇ�A
                    //�\�[�g���� += "asc "
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        l_strSort += "asc ";
                    }
                    //�Q�|�Q�|�Q�j �����^�~�� = �uD�F�~���v�̏ꍇ�A
                    //�\�[�g���� += "desc "
                    else
                    {
                        l_strSort += "desc ";
                    }

                    //�Q�|�R�j �u���v���׃e�[�u��.SORT-NO�v���\�[�g�����ɒǉ�����B
                    //�Q�|�R�|�P�j �����^�~�� = �uA�F�����v�̏ꍇ�A
                    //�\�[�g���� += ", sort_no asc "
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        l_strSort += ", sort_no asc ";
                    }
                    //�Q�|�Q�|�Q�j �����^�~�� = �uD�F�~���v�̏ꍇ�A
                    //�\�[�g���� += ", sort_no desc "
                    else
                    {
                        l_strSort += ", sort_no desc ";
                    }
                }
            }
        }

        //�R�j �p�����[�^.�\�[�g�敪 = 03�F�_�E�����[�h �̏ꍇ
        else if (WEB3TradeHistorySortDivDef.DOWNLOAD.equals(l_strSortDiv))
        {
            //�R�|�P�j �ȉ��̃\�[�g�������쐬����B
            //�E �v�Z�N�����F�@@����
            //�E SORT-NO�F�@@����
            //�\�[�g���� = "calc_date asc, sort_no asc "
            l_strSort = "calc_date asc, sort_no asc ";
        }

        //4�j �쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strSort;
    }

    /**
     * (get���v���׃t�@@�C���_�E�����[�h)<BR>
     * ���v���׃t�@@�C���_�E�����[�h�������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(���v���׏Ɖ�T�[�r�X)get���v���׃t�@@�C���_�E�����[�h�v�Q��<BR>
     * ======================================================= <BR>
     * �@@�@@�@@�@@�V�[�P���X�} :  �u(���v���׏Ɖ�T�[�r�X)get���v���׃t�@@�C���_�E�����[�h�v<BR>
     * �@@�@@�@@�@@��̈ʒu : 1.8 get���v���׌���(�ڋq, String)<BR>
     * �@@�@@�@@�@@get���v���׌���()���\�b�h�̖߂�l == 0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@���������ɍ��v���鑹�v���׃f�[�^�͂������܂���B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag           :  BUSINESS_ERROR_02666       <BR>
     * ======================================================= <BR>
     * <BR>
     * ======================================================= <BR>
     * �@@�@@�@@�@@�V�[�P���X�} :  �u(���v���׏Ɖ�T�[�r�X)get���v���׃t�@@�C���_�E�����[�h�v<BR>
     * �@@�@@�@@�@@��̈ʒu : 1.10 (*)create�c���f�[�^()�̖߂�l == null�̏ꍇ<BR>
     * �@@�@@�@@�@@create�c���f�[�^()�̖߂�l == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@���������ɍ��v���鑹�v���׃f�[�^�͂������܂���B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag           :  BUSINESS_ERROR_02666       <BR>
     * ======================================================= <BR>
     * <BR>
     * ======================================================= <BR>
     * �@@�@@�@@�@@�V�[�P���X�} :  �u(���v���׏Ɖ�T�[�r�X)get���v���׃t�@@�C���_�E�����[�h�v<BR>
     * �@@�@@�@@�@@��̈ʒu : 1.14  ���v���׍s����"get�v���t�@@�����X()�̖߂�l"�𒴂����ꍇ�A<BR>
     * �@@�@@�@@�@@��O�iBUSINESS_ERROR_01957�j��throw����B<BR>
     * �@@�@@�@@�@@���v���׍s����"get�v���t�@@�����X()�̖߂�l"�𒴂����ꍇ�A<BR>
     * �@@�@@�@@�@@�Y�����錏�����_�E�����[�h�����𒴂��Ă��܂��B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag           :  BUSINESS_ERROR_01957       <BR>
     * ======================================================= <BR>
     * <BR>
     * ======================================================= <BR>
     * �@@�@@�@@�@@�V�[�P���X�} :  �u(���v���׏Ɖ�T�[�r�X)get���v���׃t�@@�C���_�E�����[�h�v<BR>
     * �@@�@@�@@�@@��̈ʒu : 1.15 ���v���׍s����"0��"�̏ꍇ�A��O�iBUSINESS_ERROR_02666�j��throw����B<BR>
     * �@@�@@�@@�@@���v���׍s����"0��"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@���������ɍ��v���鑹�v���׃f�[�^�͂������܂���B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag           :  BUSINESS_ERROR_02666       <BR>
     * ======================================================= <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���v���׃t�@@�C���_�E�����[�h���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3PLSProfitLossDownloadResponse
     * @@roseuid 416CFDB50110
     */
    protected WEB3PLSProfitLossDownloadResponse getProfitLossDownload(
        WEB3PLSProfitLossDownloadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProfitLossDownload(WEB3PLSProfitLossDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();

        //1.2 validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3 get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.4 getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (
            WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.5 ����J�����_�R���e�L�X�g�̐ݒ�
        //  �E����J�����_�R���e�L�X�g.��t���ԋ敪 = "30�F�_�E�����[�h"
        //  ����L�ȊO�͊����l�B
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        //��t���ԋ敪���擾
        String l_strTradingTimeType = l_context.getTradingTimeType();

        //��t���ԋ敪���Z�b�g����
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOWNLOAD);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //1.6 validate�_�E�����[�h���ԑ�()
        WEB3GentradeTradingTimeManagement.validateDownloadTimeZone();

        //1.7 ����J�����_�R���e�L�X�g�̃��Z�b�g
        l_context.setTradingTimeType(l_strTradingTimeType);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //1.8get���v���׌���(�ڋq, String)
        //�ڋq�F�@@getMainAccount()�̖߂�l
        //�����敪�F�@@���N�G�X�g�f�[�^.�����敪

        WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager =
            new WEB3HistoryTradeHistoryDataManager();

        int l_intProfitLossSpecCount =
            l_historyTradeHistoryDataManager.getProfitLossSpecCount(
                l_mainAccount,
                l_request.transactionDiv);

        if (l_intProfitLossSpecCount == 0)
        {
            log.debug("get���v���׌��� == 0�̏ꍇthrow�����O");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02666,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���������ɍ��v���鑹�v���׃f�[�^�͂������܂���B");
        }

        //1.9create�c���f�[�^(�ڋq)
        WEB3PLSProfitLossSpecsUnit l_plsProfitLossSpecsUnit = this.createBalanceData(l_mainAccount);

        //1.10(*)create�c���f�[�^()�̖߂�l == null�̏ꍇ
        //throw�����O
        //BUSINESS_ERROR_02666
        //[���������ɍ��v���鑹�v���׃f�[�^�͂������܂���B]
        if (l_plsProfitLossSpecsUnit == null)
        {
            log.debug("create�c���f�[�^()�̖߂�l == null�̏ꍇthrow�����O");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02666,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���������ɍ��v���鑹�v���׃f�[�^�͂������܂���B");
        }

        //1.11 (*)���N�G�X�g�f�[�^.�����敪 == null(2�����\��) �̏ꍇ
        WEB3PLSProfitLossSpecsUnit l_createCarryoverBalanceData = null;
        List l_lisProfitLossSpecList = null;
        if (l_request.transactionDiv == null)
        {
            //1.11.1.create�J�z�c���f�[�^(�ڋq, String, ���v���׏��)
            //�ڋq�F�@@getMainAccount()�̖߂�l
            //�\�����ԁF�@@���N�G�X�g�f�[�^.�\������
            //�c�����F�@@create�c���f�[�^()�̖߂�l
            l_createCarryoverBalanceData =
                this.createCarryoverBalanceData(
                    l_mainAccount,
                    l_request.displayTerm,
                    l_plsProfitLossSpecsUnit);

            //1.11.2get���v���׈ꗗ(�ڋq, String, String, Date)
            //�ڋq�F�@@getMainAccount()�̖߂�l
            //�擾�Ώۃ��R�[�h�敪�F�@@"3�F����&���o�����R�[�h"
            //�\�����ԁF�@@���N�G�X�g�f�[�^.�\������
            //��ƔN�����F�@@null
            l_lisProfitLossSpecList = l_historyTradeHistoryDataManager.getProfitLossSpecList(
                l_mainAccount,
                WEB3PlsBvsDetailOrderRecDef.DETAIL_ORDER_REC,
                l_request.displayTerm,
                null);
        }

        //1.12(*)���N�G�X�g�f�[�^.�����敪 == 01(18�����\��) �̏ꍇ
        if (WEB3TradeHistoryTransactionDivDef.EIGHTEEN_MONTH_DISPLAY.equals(l_request.transactionDiv))
        {
            //1.12.1create��������������(Date, Date, String, String)
            //�\������From�F�@@���N�G�X�g�f�[�^.�\������From
            //�\������To�F�@@���N�G�X�g�f�[�^.�\������To
            //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
            //���i�敪�F�@@���N�G�X�g�f�[�^.���i�敪
            Date l_datListStartDate = WEB3DateUtility.getDate(l_request.listStartDate, "yyyyMMdd");
            Date l_datListEndDate = WEB3DateUtility.getDate(l_request.listEndDate, "yyyyMMdd");
            String l_strQueryString = this.createQueryString(
                l_datListStartDate,
                l_datListEndDate,
                l_request.productCode,
                l_request.commodityType);

            //1.12.2create���������f�[�^�R���e�i(Date, Date, String, String)
            //�\������From�F�@@���N�G�X�g�f�[�^.�\������From
            //�\������To�F�@@���N�G�X�g�f�[�^.�\������To
            //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
            //���i�敪�F�@@���N�G�X�g�f�[�^.���i�敪
            String[] l_strQueryDataContainers = this.createQueryDataContainer(
                l_datListStartDate,
                l_datListEndDate,
                l_request.productCode,
                l_request.commodityType);

            //1.12.3.create�\�[�g����(String, ���v���׃\�[�g�L�[)
            //�\�[�g�������쐬����B
            //�\�[�g�敪�F�@@03�F�_�E�����[�h
            //�\�[�g�L�[�F�@@null
            String l_strSortCond = this.createSortCond(
                WEB3TradeHistorySortDivDef.DOWNLOAD, null);

            //1.12.4get���v���׈ꗗ(�ڋq, String, Object[], String)
            //�ڋq�F�@@getMainAccount()�̖߂�l
            //��������������F�@@create�����󌟕�����()�̖߂�l
            //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l
            //�\�[�g�����F�@@create�\�[�g����()�̖߂�l
            l_lisProfitLossSpecList = l_historyTradeHistoryDataManager.getProfitLossSpecList(
                l_mainAccount,
                l_strQueryString,
                l_strQueryDataContainers,
                l_strSortCond);
        }

        //1.13get�v���t�@@�����X(String)
        //�ݒ薼�́F�@@�Œ蕶��"DL_REC_COUNT_PROFITLOSSLIST"
        String l_strPreferences = this.getPreferences(
            WEB3SystemPreferencesNameDef.DL_REC_COUNT_PROFITLOSSLIST);

        //1.14���v���׍s����"get�v���t�@@�����X()�̖߂�l"�𒴂����ꍇ�A
        //��O�iBUSINESS_ERROR_01957�j��throw����B
        //    �Ethrow�����O
        //  �@@�@@BUSINESS_ERROR_01957
        //  �@@�@@[�Y�����錏�����_�E�����[�h�����𒴂��Ă��܂��B]
        int l_intListCount = 0;
        if (l_lisProfitLossSpecList != null)
        {
            l_intListCount = l_lisProfitLossSpecList.size();
        }

        //�f�[�^�s�����̏ꍇ
        if (!WEB3StringTypeUtility.isInteger(l_strPreferences))
        {
            log.debug("�f�[�^�s�����̏ꍇ��O��throw����");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        if (l_intListCount > Integer.parseInt(l_strPreferences))
        {
            log.debug("���v���׍s����get�v���t�@@�����X()�̖߂�l�𒴂����ꍇ��O��throw����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01957,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y�����錏�����_�E�����[�h�����𒴂��Ă��܂��B");
        }

        //1.15���v���׍s����"0��"�̏ꍇ�A��O�iBUSINESS_ERROR_02666�j��throw����B
        // �Ethrow�����O
        // BUSINESS_ERROR_02666
        // [�Y�����錏�����_�E�����[�h�����𒴂��Ă��܂��B]
        if (l_intListCount == 0)
        {
            log.debug("���v���׍s����0���̏ꍇ�A��O�iBUSINESS_ERROR_02666�j��throw����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02666,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���������ɍ��v���鑹�v���׃f�[�^�͂������܂���B");
        }

        //1.16���v����CSV( )
        WEB3PLSProfitLossSpecsCsv l_pLSProfitLossSpecsCsv = new WEB3PLSProfitLossSpecsCsv();

        //1.16.1create�L�[�w�b�_( )
        l_pLSProfitLossSpecsCsv.createKeyHeader();

        //1.16.2create�J�����w�b�_( )
        l_pLSProfitLossSpecsCsv.createColumnHeader();

        //1.17(*)���N�G�X�g�f�[�^.�����敪 == null(2�����\��) &
        //create�J�z�c���f�[�^() != null�̏ꍇ
        if (l_request.transactionDiv == null
            && l_createCarryoverBalanceData != null)
        {
            //1.17.1add���׍s( )
            int l_intRowNumber = l_pLSProfitLossSpecsCsv.addRow();

            //1.17.2set�v�Z��(int, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //�v�Z���F�@@null
            l_pLSProfitLossSpecsCsv.setCalcDate(
                l_intRowNumber,
                " ");

            //1.17.3set��n��(int, Date)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //��n���F�@@null
            l_pLSProfitLossSpecsCsv.setDeliveryDate(
                l_intRowNumber,
                null);

            //1.17.4set�i���i�j�K�p(int, String, String, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //���i�F�@@null
            //�K�p�R�[�h�F�@@null
            //��ЃR�[�h�F�@@null
            l_pLSProfitLossSpecsCsv.setFundTypeApplication(
                l_intRowNumber,
                null,
                null,
                null);

            //1.17.5set������(String, String, String, String, String, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //���R�[�h�敪�F�@@create�J�z�c���f�[�^()�̖߂�l.���R�[�h�敪
            //�Ԋҋ��敪�F�@@null
            //���l�F�@@null
            //�������F�@@null
            //��ЃR�[�h�F�@@getMainAccount()�̖߂�l.�،���ЃR�[�h
            l_pLSProfitLossSpecsCsv.setProductName(
                l_intRowNumber + "",
                l_createCarryoverBalanceData.prolossRecDiv,
                null,
                null,
                null,
                l_mainAccount.getInstitution().getInstitutionCode());

            //1.17.6set���Z(int, String, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //���Z���敪�F�@@null
            //��ЃR�[�h�F�@@null
            l_pLSProfitLossSpecsCsv.setTerm(
                l_intRowNumber,
                null,
                null);

            //1.17.7set����(String, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //���ʁF�@@null
            l_pLSProfitLossSpecsCsv.setQuantity(
                l_intRowNumber + "",
                null);

            //1.17.8set���n��(int, Date)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //���n���F�@@null
            l_pLSProfitLossSpecsCsv.setPassDate(
                l_intRowNumber,
                null);

            //1.17.9set���n���z(int, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //���n���z�F�@@null
            l_pLSProfitLossSpecsCsv.setPassAmount(
                l_intRowNumber,
                null);

            //1.17.10set�擾��(int, Date)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //�擾���F�@@null
            l_pLSProfitLossSpecsCsv.setGetDate(
                l_intRowNumber,
                null);

            //1.17.11set�擾�(int, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //�擾��F�@@null
            l_pLSProfitLossSpecsCsv.setGetAmount(
                l_intRowNumber,
                null);

            //1.17.12set���v(int, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //���v�F�@@null
            l_pLSProfitLossSpecsCsv.setProlossAmount(
                l_intRowNumber,
                null);

            //1.17.13set�݌v���v(int, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //�݌v���v�F�@@create�J�z�c���f�[�^()�̖߂�l.�݌v���v
            l_pLSProfitLossSpecsCsv.setTotalProlossAmount(
                l_intRowNumber,
                l_createCarryoverBalanceData.totalProlossAmount);

            //1.17.14set�ېőΏۊz(int, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //�ېőΏۊz�F�@@null
            l_pLSProfitLossSpecsCsv.setTaxableAmount(
                l_intRowNumber,
                null);

            //1.17.15set�����Ŋz(int, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //�����Ŋz�F�@@create�J�z�c���f�[�^()�̖߂�l.�����Ŋz
            l_pLSProfitLossSpecsCsv.setCollectTaxAmount(
                l_intRowNumber,
                l_createCarryoverBalanceData.collectTaxAmount);

            //1.17.16set�����Ŋz�i���Łj(int, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //�����Ŋz�i���Łj�F�@@create�J�z�c���f�[�^()�̖߂�l.�����Ŋz�i���Łj
            l_pLSProfitLossSpecsCsv.setCollectTaxNAmount(
                l_intRowNumber,
                l_createCarryoverBalanceData.collectTaxNAmount);

            //1.17.17set�����Ŋz�i�n���Łj(int, String)
            //�s�ԍ��F�@@add���׍s()�̖߂�l
            //�����Ŋz�i�n���Łj�F�@@create�J�z�c���f�[�^()�̖߂�l.�����Ŋz�i�n���Łj
            l_pLSProfitLossSpecsCsv.setCollectTaxLAmount(
                l_intRowNumber,
                l_createCarryoverBalanceData.collectTaxLAmount);
        }

        //1.18(*)�擾�����w���ׁE���o���f�[�^�x�̌�����Loop����
        for (int i = 0; i < l_intListCount; i++)
        {
            ProfitLossSpecRow l_profitLossSpecRow = (ProfitLossSpecRow)l_lisProfitLossSpecList.get(i);
            if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecRow.getRecDiv())
                || WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
            {
                //1.18.1add���׍s( )
                int l_intRowNumber = l_pLSProfitLossSpecsCsv.addRow();

                //1.18.2set�v�Z��(int, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�v�Z���F�@@get���v���׈ꗗ()�̖߂�l[index].�v�Z��
                l_pLSProfitLossSpecsCsv.setCalcDate(
                    l_intRowNumber,
                    WEB3DateUtility.formatDate(l_profitLossSpecRow.getCalcDate(), "yyyy/MM/dd"));

                //1.18.3set��n��(int, Date)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //��n���F�@@get���v���׈ꗗ()�̖߂�l[index].��n��
                l_pLSProfitLossSpecsCsv.setDeliveryDate(
                    l_intRowNumber,
                    l_profitLossSpecRow.getDeliveryDate());

                //1.18.4set�i���i�j�K�p(int, String, String, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //���i�F�@@get���v���׈ꗗ()�̖߂�l[index].���i (*1)
                //�K�p�R�[�h�F�@@get���v���׈ꗗ()�̖߂�l[index].�K�p�R�[�h (*1)
                //��ЃR�[�h�F�@@getMainAccount()�̖߂�l.�،���ЃR�[�h (*1)
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //    21�F���o�����R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setFundTypeApplication(
                        l_intRowNumber,
                        null,
                        null,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setFundTypeApplication(
                        l_intRowNumber,
                        l_profitLossSpecRow.getCommodityDiv(),
                        l_profitLossSpecRow.getApplicationCode(),
                        l_mainAccount.getInstitution().getInstitutionCode());
                }

                //1.18.5set������(String, String, String, String, String, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //���R�[�h�敪�F�@@get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪
                //�Ԋҋ��敪�F�@@get���v���׈ꗗ()�̖߂�l[index].�Ԋҋ��敪
                //���l�F�@@get���v���׈ꗗ()�̖߂�l[index].���l
                //�������F�@@get���v���׈ꗗ()�̖߂�l[index].������
                //��ЃR�[�h�F�@@getMainAccount()�̖߂�l.�،���ЃR�[�h
                l_pLSProfitLossSpecsCsv.setProductName(
                    l_intRowNumber + "",
                    l_profitLossSpecRow.getRecDiv(),
                    l_profitLossSpecRow.getReturnDiv(),
                    l_profitLossSpecRow.getRemark(),
                    l_profitLossSpecRow.getStandardName(),
                    l_mainAccount.getInstitution().getInstitutionCode());

                //1.18.6set���Z(int, String, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //���Z���敪�F�@@get���v���׈ꗗ()�̖߂�l[index].���Z���敪(*1)
                //��ЃR�[�h�F�@@getMainAccount()�̖߂�l.�،���ЃR�[�h (*1)
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪
                //= 21�F���o�����R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setTerm(
                        l_intRowNumber,
                        null,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setTerm(
                        l_intRowNumber,
                        l_profitLossSpecRow.getTermDiv(),
                        l_mainAccount.getInstitution().getInstitutionCode());
                }

                //1.18.7set����(String, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //���ʁF�@@get���v���׈ꗗ()�̖߂�l[index].���� / 100000 (*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //21�F���o�����R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��.
                String l_strQuantity =
                    WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getQuantity() / 100000);
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_strQuantity = null;
                }

                l_pLSProfitLossSpecsCsv.setQuantity(
                    l_intRowNumber + "",
                    l_strQuantity);

                //1.18.8set���n��(int, Date)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //���n���F�@@get���v���׈ꗗ()�̖߂�l[index].���n��(*1)
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //21�F���o�����R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setPassDate(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setPassDate(
                        l_intRowNumber,
                        l_profitLossSpecRow.getPassDate());
                }

                //1.18.9set���n���z(int, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //���n���z�F�@@get���v���׈ꗗ()�̖߂�l[index].���n���z (*1)
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //21�F���o�����R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setPassAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setPassAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getPassAmount()));
                }

                //1.18.10set�擾��(int, Date)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�擾���F�@@get���v���׈ꗗ()�̖߂�l[index].�擾�� (*1)
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //21�F���o�����R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setGetDate(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setGetDate(
                        l_intRowNumber,
                        l_profitLossSpecRow.getGetDate());
                }

                //1.18.11set�擾�(int, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�擾��F�@@get���v���׈ꗗ()�̖߂�l[index].�擾� (*1)
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //21�F���o�����R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setGetAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setGetAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getGetAmount()));
                }

                //1.18.12set���v(int, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //���v�F�@@get���v���׈ꗗ()�̖߂�l[index].���v (*1)
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 = 21�F���o�����R�[�h]�@@���A
                //[get���v���׈ꗗ()�̖߂�l[index].�Ԋҋ��敪 = 1�F�Ԋҋ�]�@@�̏ꍇ�ɂ�
                //����.���v�ɂ� null ��ݒ肷��B
                String l_strProlossAmount = WEB3StringTypeUtility.formatNumber(
                    l_profitLossSpecRow.getProlossAmount());

                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv())
                    && WEB3ReturnDivDef.RETURN.equals(l_profitLossSpecRow.getReturnDiv()))
                {
                    l_strProlossAmount = null;
                }
                l_pLSProfitLossSpecsCsv.setProlossAmount(
                    l_intRowNumber,
                    l_strProlossAmount);

                //1.18.13set�݌v���v(int, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�݌v���v�F�@@get���v���׈ꗗ()�̖߂�l[index].�݌v���v (*1)
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //21�F���o�����R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setTotalProlossAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setTotalProlossAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getTotalProlossAmount()));
                }

                //1.18.14set�ېőΏۊz(int, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�ېőΏۊz�F�@@get���v���׈ꗗ()�̖߂�l[index].�ېőΏۊz (*1)
                //
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 = 21�F���o�����R�[�h]�@@����
                //[get���v���׈ꗗ()�̖߂�l[index].�Ԋҋ��敪 = 1�F�Ԋҋ�]�@@�̏ꍇ�ɂ́A
                //����.�ېőΏۊz�ɂ� null ��ݒ肷��B
                //(*2)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //20�F���׃��R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                String l_strTaxableAmount = WEB3StringTypeUtility.formatNumber(
                    l_profitLossSpecRow.getTaxableAmount());

                if ((WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv())
                    && WEB3ReturnDivDef.RETURN.equals(l_profitLossSpecRow.getReturnDiv()))
                    || WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecRow.getRecDiv()))
                    {
                        l_strTaxableAmount = null;
                    }

                l_pLSProfitLossSpecsCsv.setTaxableAmount(
                    l_intRowNumber,
                    l_strTaxableAmount);

                //1.18.15set�����Ŋz(int, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�����Ŋz�F�@@get���v���׈ꗗ()�̖߂�l[index].�����Ŋz (*1)
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //20�F���׃��R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getCollectTaxAmount()));
                }

                //1.18.16set�����Ŋz�i���Łj(int, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�����Ŋz�i���Łj�F�@@get���v���׈ꗗ()�̖߂�l[index].�����Ŋz�i���Łj(*1)
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //20�F���׃��R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxNAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxNAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getCollectTaxNAmount()));
                }

                //1.18.17set�����Ŋz�i�n���Łj(int, String)
                //�s�ԍ��F�@@add���׍s()�̖߂�l
                //�����Ŋz�i�n���Łj�F�@@get���v���׈ꗗ()�̖߂�l[index].�����Ŋz�i�n���Łj
                //(*1)
                //[get���v���׈ꗗ()�̖߂�l[index].���R�[�h�敪 =
                //20�F���׃��R�[�h]�̏ꍇ�ɂ� null ��ݒ肷��B
                if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxLAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxLAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getCollectTaxLAmount()));
                }
            }
        }

        //1.19add���׍s( )
        int l_intRowNumber = l_pLSProfitLossSpecsCsv.addRow();

        //1.20set�v�Z��(int, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //�v�Z���F�@@null
        l_pLSProfitLossSpecsCsv.setCalcDate(
            l_intRowNumber,
            " ");

        //1.21set��n��(int, Date)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //��n���F�@@null
        l_pLSProfitLossSpecsCsv.setDeliveryDate(
            l_intRowNumber,
            null);

        //1.22set�i���i�j�K�p(int, String, String, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //���i�F�@@null
        //�K�p�R�[�h�F�@@null
        //��ЃR�[�h�F�@@null
        l_pLSProfitLossSpecsCsv.setFundTypeApplication(
            l_intRowNumber,
            null,
            null,
            null);

        //1.23set������(String, String, String, String, String, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //���R�[�h�敪�F�@@create�c���f�[�^()�̖߂�l.���R�[�h�敪
        //�Ԋҋ��敪�F�@@null
        //���l�F�@@null
        //�������F�@@null
        //��ЃR�[�h�F�@@getMainAccount()�̖߂�l.�،���ЃR�[�h
        l_pLSProfitLossSpecsCsv.setProductName(
            l_intRowNumber + "",
            l_plsProfitLossSpecsUnit.prolossRecDiv,
            null,
            null,
            null,
            l_mainAccount.getInstitution().getInstitutionCode());

        //1.24set���Z(int, String, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //���Z���敪�F�@@null
        //��ЃR�[�h�F�@@null
        l_pLSProfitLossSpecsCsv.setTerm(
            l_intRowNumber,
            null,
            null);

        //1.25set����(String, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //���ʁF�@@null
        l_pLSProfitLossSpecsCsv.setQuantity(
            l_intRowNumber + "",
            null);

        //1.26set���n��(int, Date)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //���n���F�@@null
        l_pLSProfitLossSpecsCsv.setPassDate(
            l_intRowNumber,
            null);

        //1.27set���n���z(int, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //���n���z�F�@@create�c���f�[�^()�̖߂�l.���n���z
        l_pLSProfitLossSpecsCsv.setPassAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.passAmount);

        //1.28set�擾��(int, Date)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //�擾���F�@@null
        l_pLSProfitLossSpecsCsv.setGetDate(
            l_intRowNumber,
            null);

        //1.29set�擾�(int, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //�擾��F�@@create�c���f�[�^()�̖߂�l.�擾�
        l_pLSProfitLossSpecsCsv.setGetAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.getAmount);

        //1.30set���v(int, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //���v�F�@@null
        l_pLSProfitLossSpecsCsv.setProlossAmount(
            l_intRowNumber,
            null);

        //1.31set�݌v���v(int, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //�݌v���v�F�@@create�c���f�[�^()�̖߂�l.�݌v���v
        l_pLSProfitLossSpecsCsv.setTotalProlossAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.totalProlossAmount);

        //1.32set�ېőΏۊz(int, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //�ېőΏۊz�F�@@null
        l_pLSProfitLossSpecsCsv.setTaxableAmount(
            l_intRowNumber,
            null);

        //1.33set�����Ŋz(int, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //�����Ŋz�F�@@create�c���f�[�^()�̖߂�l.�����Ŋz
        l_pLSProfitLossSpecsCsv.setCollectTaxAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.collectTaxAmount);

        //1.34set�����Ŋz�i���Łj(int, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //�����Ŋz�i���Łj�F�@@create�c���f�[�^()�̖߂�l.�����Ŋz�i���Łj
        l_pLSProfitLossSpecsCsv.setCollectTaxNAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.collectTaxNAmount);

        //1.35set�����Ŋz�i�n���Łj(int, String)
        //�s�ԍ��F�@@add���׍s()�̖߂�l
        //�����Ŋz�i�n���Łj�F�@@create�c���f�[�^()�̖߂�l.�����Ŋz�i�n���Łj
        l_pLSProfitLossSpecsCsv.setCollectTaxLAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.collectTaxLAmount);

        //1.36 getCSV�t�@@�C���s( )

        String[] l_strCsvFileLines = l_pLSProfitLossSpecsCsv.getCsvFileLines();

        //1.37createResponse( )
        WEB3PLSProfitLossDownloadResponse l_response =
            (WEB3PLSProfitLossDownloadResponse)l_request.createResponse();

        //1.38 (*1)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�_�E�����[�h�t�@@�C��  ���@@getCSV�t�@@�C���s()�̖߂�l
        l_response.downloadFile = l_strCsvFileLines;

        //���ݓ���        ���@@TradingSystem.getSystemTimestamp()�̖߂�l
        l_response.currentDate = WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�v���t�@@�����X)<BR>
     * �p�����[�^�Ɏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���V�X�e���v���t�@@�����X�e�[�u������擾����B<BR>
     * <BR>
     * �P�j�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B<BR>
     * <BR>
     * �@@[�擾����]<BR>
     * �@@���́i���ϐ����j = �p�����[�^.�ݒ薼��<BR>
     * <BR>
     * �Q�j�擾�����V�X�e���v���t�@@�����X�e�[�u���̃��R�[�h�̐ݒ�l��ԋp����B<BR>
     * @@param l_strPreferences - (�ݒ薼��)<BR>
     * �V�X�e���v���t�@@�����X����<BR>
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String getPreferences(String l_strPreferences) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPreferences(String) ";
        log.entering(STR_METHOD_NAME);

        //�P�j�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B
        // �@@[�擾����]
        // �@@���́i���ϐ����j = �p�����[�^.�ݒ薼��
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" name = ? ");

        Object[] l_objWhere = {l_strPreferences};

        List l_lisRecords = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                SystemPreferencesRow.TYPE,
                l_strWhere.toString(),
                null,
                null,
                l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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

        //�Q�j�擾�����V�X�e���v���t�@@�����X�e�[�u���̃��R�[�h�̐ݒ�l��ԋp����B
        String l_strValue = null;
        if (l_lisRecords.size() != 0)
        {
            SystemPreferencesRow l_preferencesRow = (SystemPreferencesRow)l_lisRecords.get(0);

            l_strValue = l_preferencesRow.getValue();

        }

        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }

}
@
