head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPHistoryTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPHistoryTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/07 張少傑　@(中訊)　@新規作成
Revision History : 2008/10/28 陸文静　@(中訊)　@仕様変更　@モデルNo325
*/
package webbroker3.tradingpower.updtpower.contract;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPContractAmountApplyDateDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPHistoryTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPHistoryTest.class);
    WEB3TPHistory l_tPHistory = null;
    private int k = 0;
    public WEB3TPHistoryTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_tPHistory = new WEB3TPHistory();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //calc決済益
    public void testCalcContractProfit_C001()
    {
        final String STR_METHOD_NAME = "testCalcContractProfit_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);//建区分 = 「1：買建」 の場合 
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "1");
            map.put("swap.contract.profitloss.restraint", "0");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_CLOSE_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            
            l_tPHistory.setCommissionFee(10);
            l_tPHistory.setCommissionFeeTax(20);
            l_tPHistory.setImportedSetupFee(30);
            l_tPHistory.setImportedSetupFeeTax(40);
            l_tPHistory.setImportedNameTransferFee(50);
            l_tPHistory.setImportedNameTransferFeeTax(60);
            l_tPHistory.setImportedManagementFee(70);
            l_tPHistory.setImportedManagementFeeTax(80);
            l_tPHistory.setImportedInterestFee(90);
            l_tPHistory.setImportedPayInterestFee(100);
            l_tPHistory.setNetAmount(10);
            
            double l_dblReturn = l_tPHistory.calcContractProfit();
            
            assertEquals("360.0", l_dblReturn+"");
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc決済益
    public void testCalcContractProfit_C002()
    {
        final String STR_METHOD_NAME = "testCalcContractProfit_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);//建区分 = 「2：売建」 の場合 
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "1");
            map.put("swap.contract.profitloss.restraint", "0");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_CLOSE_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            l_tPHistory.setCommissionFee(10);
            l_tPHistory.setCommissionFeeTax(20);
            l_tPHistory.setImportedSetupFee(30);
            l_tPHistory.setImportedSetupFeeTax(40);
            l_tPHistory.setImportedNameTransferFee(50);
            l_tPHistory.setImportedNameTransferFeeTax(60);
            l_tPHistory.setImportedManagementFee(70);
            l_tPHistory.setImportedManagementFeeTax(80);
            l_tPHistory.setImportedInterestFee(900);
            l_tPHistory.setImportedPayInterestFee(100);
            l_tPHistory.setNetAmount(10);
            l_tPHistory.setImportedLoanEquityFee(50);
            double l_dblReturn = l_tPHistory.calcContractProfit();
            
            assertEquals("0.0", l_dblReturn+"");
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc決済益
    public void testCalcContractProfit_C003()
    {
        final String STR_METHOD_NAME = "testCalcContractProfit_C003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);//建区分 = 「2：売建」 の場合 
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "1");
            map.put("swap.contract.profitloss.restraint", "0");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_OPEN_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
//            l_tPHistory.setContractTotalCost(50);
            l_tPHistory.setImportedPayInterestFee(80);
            l_tPHistory.setNetAmount(10);
            l_tPHistory.setImportedInterestFee(20);
            
            double l_dblReturn = l_tPHistory.calcContractProfit();
            
            assertEquals("0.0", l_dblReturn+"");
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc決済損 
    public void testCalcContractLoss_C001()
    {
        final String STR_METHOD_NAME = "testCalcContractLoss_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);//建区分 = 「1：買建」 の場合 
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "1");
            map.put("swap.contract.profitloss.restraint", "0");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_CLOSE_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            l_tPHistory.setCommissionFee(10);
            l_tPHistory.setCommissionFeeTax(20);
            l_tPHistory.setImportedSetupFee(30);
            l_tPHistory.setImportedSetupFeeTax(40);
            l_tPHistory.setImportedNameTransferFee(50);
            l_tPHistory.setImportedNameTransferFeeTax(60);
            l_tPHistory.setImportedManagementFee(70);
            l_tPHistory.setImportedManagementFeeTax(80);
            l_tPHistory.setImportedInterestFee(90);
            l_tPHistory.setImportedPayInterestFee(1000);
            l_tPHistory.setNetAmount(10);
            
            double l_dblReturn = l_tPHistory.calcContractLoss();
            
            assertEquals("540.0", l_dblReturn+"");
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc決済損 
    public void testCalcContractLoss_C002()
    {
        final String STR_METHOD_NAME = "testCalcContractLoss_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);//建区分 = 「2：売建」 の場合 
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "1");
            map.put("swap.contract.profitloss.restraint", "0");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_CLOSE_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            l_tPHistory.setCommissionFee(10);
            l_tPHistory.setCommissionFeeTax(20);
            l_tPHistory.setImportedSetupFee(30);
            l_tPHistory.setImportedSetupFeeTax(40);
            l_tPHistory.setImportedNameTransferFee(50);
            l_tPHistory.setImportedNameTransferFeeTax(60);
            l_tPHistory.setImportedManagementFee(70);
            l_tPHistory.setImportedManagementFeeTax(80);
            l_tPHistory.setImportedInterestFee(90);
            l_tPHistory.setImportedPayInterestFee(100);
            l_tPHistory.setNetAmount(10);
            l_tPHistory.setImportedLoanEquityFee(50);
            
            double l_dblReturn = l_tPHistory.calcContractLoss();
            
            assertEquals("0.0", l_dblReturn+"");
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc決済損 
    public void testCalcContractLoss_C003()
    {
        final String STR_METHOD_NAME = "testCalcContractLoss_C003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);//建区分 = 「2：売建」 の場合 
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "1");
            map.put("swap.contract.profitloss.restraint", "0");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_OPEN_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
//            l_tPHistory.setContractTotalCost(50);
            l_tPHistory.setImportedPayInterestFee(80);
            l_tPHistory.setNetAmount(10);
            l_tPHistory.setImportedInterestFee(20);
            
            double l_dblReturn = l_tPHistory.calcContractLoss();
            
            assertEquals("0.0", l_dblReturn+"");
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //未受渡建玉<日計り返済>の場合,返回true
    public void testIsDayTradeSwap_C001()
    {
        final String STR_METHOD_NAME = "testIsDayTradeSwap_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        l_targetContract.setTargetContractDetail(l_targetContractDetail);
        WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
        l_tPCalcCondition.setEquityNextDayOrderStartDiv(false);
        Date[] l_datBizDate = new Date[3];
        l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
        l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
        l_tPCalcCondition.setBizDate(l_datBizDate);

        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_CLOSE_MARGIN;
        l_tPHistory.setTransactionCateg(l_transactionCateg);
        l_tPHistory.setCalcCondition(l_tPCalcCondition);
        l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        l_tPHistory.setExecuted(true);
        l_tPHistory.setTargetContract(l_targetContract);
        boolean l_blnReturn = l_tPHistory.isDayTradeSwap("0");
        assertEquals(false, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //未約定現引現渡 の場合，返回true
    public void testIsDayTradeSwap_C002()
    {
        final String STR_METHOD_NAME = "testIsDayTradeSwap_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
        l_targetContract.setTargetContractDetail(l_targetContractDetail);
        WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
        l_tPCalcCondition.setEquityNextDayOrderStartDiv(false);
        Date[] l_datBizDate = new Date[3];
        l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
        l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
        l_tPCalcCondition.setBizDate(l_datBizDate);

        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_SWAP_MARGIN;
        l_tPHistory.setTransactionCateg(l_transactionCateg);
        l_tPHistory.setCalcCondition(l_tPCalcCondition);
        l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        l_tPHistory.setExecuted(true);
        l_tPHistory.setTargetContract(l_targetContract);
        l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        boolean l_blnReturn = l_tPHistory.isDayTradeSwap("0");
        
        assertEquals(false, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //非未約定現引現渡 の場合，返回false
    public void testIsDayTradeSwap_C003()
    {
        final String STR_METHOD_NAME = "testIsDayTradeSwap_C003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "0");
            map.put("swap.contract.profitloss.restraint", "1");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_SWAP_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040720", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            boolean l_blnReturn = l_tPHistory.isDayTradeSwap("1");
            
            assertEquals(false, l_blnReturn);
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //非未約定現引現渡 の場合，返回false
    public void testIsDayTradeSwap_C004()
    {
        final String STR_METHOD_NAME = "testIsDayTradeSwap_C004";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "0");
            map.put("swap.contract.profitloss.restraint", "0");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_SWAP_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            boolean l_blnReturn = l_tPHistory.isDayTradeSwap("2");
            
            assertEquals(false, l_blnReturn);
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //非未約定現引現渡 の場合，返回false
    public void testIsDayTradeSwap_C005()
    {
        final String STR_METHOD_NAME = "testIsDayTradeSwap_C005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "0");
            map.put("swap.contract.profitloss.restraint", "0");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_SWAP_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            boolean l_blnReturn = l_tPHistory.isDayTradeSwap("0");
            
            assertEquals(false, l_blnReturn);
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //非未約定現引現渡 の場合，返回true
    public void testIsDayTradeSwap_C006()
    {
        final String STR_METHOD_NAME = "testIsDayTradeSwap_C006";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "1");
            map.put("swap.contract.profitloss.restraint", "0");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_SWAP_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            boolean l_blnReturn = l_tPHistory.isDayTradeSwap("1");
            
            assertEquals(true, l_blnReturn);
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //非未約定現引現渡 の場合，返回true
    public void testIsDayTradeSwap_C007()
    {
        final String STR_METHOD_NAME = "testIsDayTradeSwap_C007";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "1");
            map.put("swap.contract.profitloss.restraint", "1");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");
    
            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_SWAP_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            boolean l_blnReturn = l_tPHistory.isDayTradeSwap("2");
            
            assertEquals(true, l_blnReturn);
        }
        catch(Exception e)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //非未受渡建玉<非日計り返済>の場合,返回false
    public void testIsUndeliveredContractNotDayTrade_C001()
    {
        final String STR_METHOD_NAME = "testIsUndeliveredContractNotDayTrade_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
        l_targetContract.setTargetContractDetail(l_targetContractDetail);
        WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
        l_tPCalcCondition.setEquityNextDayOrderStartDiv(false);
        Date[] l_datBizDate = new Date[3];
        l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
        l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
        l_tPCalcCondition.setBizDate(l_datBizDate);

        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_CLOSE_MARGIN;
        l_tPHistory.setTransactionCateg(l_transactionCateg);
        l_tPHistory.setCalcCondition(l_tPCalcCondition);
        l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        l_tPHistory.setExecuted(true);
        l_tPHistory.setTargetContract(l_targetContract);
        l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040720", "yyyyMMdd"));
        
        boolean l_blnReturn = l_tPHistory.isUndeliveredContractNotDayTradeSwap("0");
        assertEquals(false, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //非未受渡建玉<非日計り返済>の場合,返回false
    public void testIsUndeliveredContractNotDayTrade_C002()
    {
        final String STR_METHOD_NAME = "testIsUndeliveredContractNotDayTrade_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
        l_targetContract.setTargetContractDetail(l_targetContractDetail);
        WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
        l_tPCalcCondition.setEquityNextDayOrderStartDiv(false);
        Date[] l_datBizDate = new Date[3];
        l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
        l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
        l_tPCalcCondition.setBizDate(l_datBizDate);

        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_OPEN_MARGIN;
        l_tPHistory.setTransactionCateg(l_transactionCateg);
        l_tPHistory.setCalcCondition(l_tPCalcCondition);
        l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        l_tPHistory.setExecuted(true);
        l_tPHistory.setTargetContract(l_targetContract);
        l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040720", "yyyyMMdd"));
        
        boolean l_blnReturn = l_tPHistory.isUndeliveredContractNotDayTradeSwap("0");
        assertEquals(false, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //非未受渡建玉<非日計り返済>の場合,返回false
    public void testIsUndeliveredContractNotDayTrade_C003()
    {
        final String STR_METHOD_NAME = "testIsUndeliveredContractNotDayTrade_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
        l_targetContract.setTargetContractDetail(l_targetContractDetail);
        WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
        l_tPCalcCondition.setEquityNextDayOrderStartDiv(false);
        Date[] l_datBizDate = new Date[3];
        l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
        l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
        l_tPCalcCondition.setBizDate(l_datBizDate);

        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_CLOSE_MARGIN;
        l_tPHistory.setTransactionCateg(l_transactionCateg);
        l_tPHistory.setCalcCondition(l_tPCalcCondition);
        l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        l_tPHistory.setExecuted(false);
        l_tPHistory.setTargetContract(l_targetContract);
        l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040720", "yyyyMMdd"));
        
        boolean l_blnReturn = l_tPHistory.isUndeliveredContractNotDayTradeSwap("0");
        assertEquals(false, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //非未受渡建玉<非日計り返済>の場合,返回false
    public void testIsUndeliveredContractNotDayTrade_C004()
    {
        final String STR_METHOD_NAME = "testIsUndeliveredContractNotDayTrade_C004";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
        l_targetContract.setTargetContractDetail(l_targetContractDetail);
        WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
        l_tPCalcCondition.setEquityNextDayOrderStartDiv(false);
        Date[] l_datBizDate = new Date[3];
        l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
        l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
        l_tPCalcCondition.setBizDate(l_datBizDate);

        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_CLOSE_MARGIN;
        l_tPHistory.setTransactionCateg(l_transactionCateg);
        l_tPHistory.setCalcCondition(l_tPCalcCondition);
        l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        l_tPHistory.setExecuted(true);
        l_tPHistory.setTargetContract(l_targetContract);
        l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
        
        boolean l_blnReturn = l_tPHistory.isUndeliveredContractNotDayTradeSwap("0");
        assertEquals(false, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //未受渡建玉<非日計り返済>の場合,返回true
    public void testIsUndeliveredContractNotDayTrade_C005()
    {
        final String STR_METHOD_NAME = "testIsUndeliveredContractNotDayTrade_C005";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
        l_targetContract.setTargetContractDetail(l_targetContractDetail);
        WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
        l_tPCalcCondition.setEquityNextDayOrderStartDiv(true);
        Date[] l_datBizDate = new Date[3];
        l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
        l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
        l_tPCalcCondition.setBizDate(l_datBizDate);

        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_SWAP_MARGIN;
        l_tPHistory.setTransactionCateg(l_transactionCateg);
        l_tPHistory.setCalcCondition(l_tPCalcCondition);
        l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
        l_tPHistory.setExecuted(true);
        l_tPHistory.setTargetContract(l_targetContract);
        l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
        
        boolean l_blnReturn = l_tPHistory.isUndeliveredContractNotDayTradeSwap("0");
        assertEquals(true, l_blnReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //未受渡建玉<非日計り返済>の場合,返回true
    public void testIsUndeliveredContractNotDayTrade_C006()
    {
        final String STR_METHOD_NAME = "testIsUndeliveredContractNotDayTrade_C006";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(false);

            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "0");
            map.put("swap.contract.profitloss.restraint", "1");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");

            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
        
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
    
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_SWAP_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            
            boolean l_blnReturn = l_tPHistory.isUndeliveredContractNotDayTradeSwap("1");
            assertEquals(true, l_blnReturn);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //未受渡建玉<非日計り返済>の場合,返回true
    public void testIsUndeliveredContractNotDayTrade_C007()
    {
        final String STR_METHOD_NAME = "testIsUndeliveredContractNotDayTrade_C007";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setEquityNextDayOrderStartDiv(false);

            Map map = new HashMap();
            map.put("swap.contract.deposit.restraint", "1");
            map.put("swap.contract.profitloss.restraint", "1");
            
            Field field;
            field = WEB3TPCalcCondition.class.getDeclaredField("instBranCalcCondition");

            field.setAccessible(true);
            field.set(l_tPCalcCondition, map);
        
            Date[] l_datBizDate = new Date[3];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040720", "yyyyMMdd");
            l_tPCalcCondition.setBizDate(l_datBizDate);
    
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_SWAP_MARGIN;
            l_tPHistory.setTransactionCateg(l_transactionCateg);
            l_tPHistory.setCalcCondition(l_tPCalcCondition);
            l_tPHistory.setTransactionDate(WEB3DateUtility.getDate("20040716", "yyyyMMdd"));
            l_tPHistory.setExecuted(true);
            l_tPHistory.setTargetContract(l_targetContract);
            l_tPHistory.setDeliveryDate(WEB3DateUtility.getDate("20040722", "yyyyMMdd"));
            
            boolean l_blnReturn = l_tPHistory.isUndeliveredContractNotDayTradeSwap("1");
            assertEquals(false, l_blnReturn);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //２）トランザクションカテゴリ＝新規建の場合
    //a）対象建玉詳細.建日＞＝余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
    //・変動反映開始日に建日 + 当日建玉代金計上開始基準日をセットする。
    public void testCalcReflectDay_C001()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_C001";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datDate = WEB3DateUtility.getDate("20081028","yyyyMMdd");
            Date l_datOpen = WEB3DateUtility.getDate("20091020","yyyyMMdd");
            
            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_OPEN_MARGIN;
            Date[] l_datBizDate = new Date[9];

            for(int i = 0;i < 9;i++)
            {
                l_datBizDate[i] = WEB3DateUtility.getDate("20091020","yyyyMMdd");
            }

            l_targetContractDetail.setOpenDate(l_datOpen);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPHistory l_tpHistory = new WEB3TPHistory();
            l_tpHistory.setCalcCondition(l_calcConditionForTest);
            l_tpHistory.setTargetContract(l_targetContract);
            l_calcConditionForTest.setBizDate(l_datBizDate);
            
            l_tpHistory.setTransactionCateg(l_transactionCateg);

            l_tpHistory.calcReflectDay(l_datDate);
            assertEquals("20091020",WEB3DateUtility.formatDate(l_tpHistory.getReflectStartDay(),"yyyyMMdd"));
            assertEquals("20091020",WEB3DateUtility.formatDate(l_tpHistory.getReflectEndDay(),"yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //２）トランザクションカテゴリ＝新規建の場合
    //・変動反映開始日に営業日(T+0)をセットする。
    public void testCalcReflectDay_C002()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_C002";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datDate = WEB3DateUtility.getDate("20081028","yyyyMMdd");
            Date l_datOpen = WEB3DateUtility.getDate("20081010","yyyyMMdd");

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_OPEN_MARGIN;
            Date[] l_datBizDate = new Date[9];

            for(int i = 0;i < 9;i++)
            {
                l_datBizDate[i] = WEB3DateUtility.getDate("20081210","yyyyMMdd");
            }

            l_targetContractDetail.setOpenDate(l_datOpen);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPHistory l_tpHistory = new WEB3TPHistory();
            l_tpHistory.setCalcCondition(l_calcConditionForTest);
            l_tpHistory.setTargetContract(l_targetContract);
            l_calcConditionForTest.setBizDate(l_datBizDate);
            
            l_tpHistory.setTransactionCateg(l_transactionCateg);

            l_tpHistory.calcReflectDay(l_datDate);
            assertEquals("20081210",WEB3DateUtility.formatDate(l_tpHistory.getReflectStartDay(),"yyyyMMdd"));
            assertEquals("20081210",WEB3DateUtility.formatDate(l_tpHistory.getReflectEndDay(),"yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //３）トランザクションカテゴリ＝返済 or 現引現渡の場合
    //a）対象建玉詳細.建日＞＝余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
    //・変動反映開始日に建日 + 当日建玉代金計上開始基準日をセットする。
    public void testCalcReflectDay_C003()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_C003";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datDate = WEB3DateUtility.getDate("20091020","yyyyMMdd");
            Date l_datOpen = WEB3DateUtility.getDate("20091020","yyyyMMdd");

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_CLOSE_MARGIN;
            Date[] l_datBizDate = new Date[9];

            l_datBizDate[0] = WEB3DateUtility.getDate("20091010","yyyyMMdd");
            for(int i = 1;i  < 9;i++)
            {
                l_datBizDate[i] = WEB3DateUtility.getDate("20091020","yyyyMMdd");
            }

            l_targetContractDetail.setOpenDate(l_datOpen);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPHistory l_tpHistory = new WEB3TPHistory();
            l_tpHistory.setCalcCondition(l_calcConditionForTest);
            l_tpHistory.setTargetContract(l_targetContract);
            l_calcConditionForTest.setBizDate(l_datBizDate);
            
            l_tpHistory.setTransactionCateg(l_transactionCateg);

            l_tpHistory.calcReflectDay(l_datDate);
            assertEquals("20091020",WEB3DateUtility.formatDate(l_tpHistory.getReflectStartDay(),"yyyyMMdd"));
            assertEquals("20091010",WEB3DateUtility.formatDate(l_tpHistory.getReflectEndDay(),"yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //３）トランザクションカテゴリ＝返済 or 現引現渡の場合
    //a）対象建玉詳細.建日＞＝余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
    //・変動反映開始日に営業日(T+0)をセットする。
    public void testCalcReflectDay_C004()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_C004";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datDate = WEB3DateUtility.getDate("20091015","yyyyMMdd");
            Date l_datOpen = WEB3DateUtility.getDate("20071015","yyyyMMdd");

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_CLOSE_MARGIN;
            Date[] l_datBizDate = new Date[9];

            l_datBizDate[0] = WEB3DateUtility.getDate("20091020","yyyyMMdd");
            for(int i = 1;i  < 9;i++)
            {
                l_datBizDate[i] = WEB3DateUtility.getDate("20091015","yyyyMMdd");
            }

            l_targetContractDetail.setOpenDate(l_datOpen);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPHistory l_tpHistory = new WEB3TPHistory();
            l_tpHistory.setCalcCondition(l_calcConditionForTest);
            l_tpHistory.setTargetContract(l_targetContract);
            l_calcConditionForTest.setBizDate(l_datBizDate);
            
            l_tpHistory.setTransactionCateg(l_transactionCateg);

            l_tpHistory.calcReflectDay(l_datDate);
            assertEquals("20091015",WEB3DateUtility.formatDate(l_tpHistory.getReflectStartDay(),"yyyyMMdd"));
            assertEquals("20091020",WEB3DateUtility.formatDate(l_tpHistory.getReflectEndDay(),"yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //当日建玉代金計上開始基準日に1をセットする。
    //２）トランザクションカテゴリ＝新規建の場合
    //対象建玉詳細.建日＜余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
    //・変動反映開始日に営業日(T+0)をセットする。
    public void testCalcReflectDay_C005()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_C005";
        log.entering(STR_METHOD_NAME);

        try
        {
            k = 1;
            Date l_datDate = WEB3DateUtility.getDate("20081028","yyyyMMdd");
            Date l_datOpen = WEB3DateUtility.getDate("20081010","yyyyMMdd");

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_OPEN_MARGIN;
            Date[] l_datBizDate = new Date[9];

            l_datBizDate[0] = WEB3DateUtility.getDate("20081211","yyyyMMdd");
            for(int i = 1;i < 9;i++)
            {
                l_datBizDate[i] = WEB3DateUtility.getDate("20081210","yyyyMMdd");
            }

            l_targetContractDetail.setOpenDate(l_datOpen);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPHistory l_tpHistory = new WEB3TPHistory();
            l_tpHistory.setCalcCondition(l_calcConditionForTest);
            l_tpHistory.setTargetContract(l_targetContract);
            l_calcConditionForTest.setBizDate(l_datBizDate);
            
            l_tpHistory.setTransactionCateg(l_transactionCateg);

            l_tpHistory.calcReflectDay(l_datDate);
            assertEquals("20081210",WEB3DateUtility.formatDate(l_tpHistory.getReflectStartDay(),"yyyyMMdd"));
            assertEquals("20081210",WEB3DateUtility.formatDate(l_tpHistory.getReflectEndDay(),"yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //当日建玉代金計上開始基準日に2をセットする。
    //２）トランザクションカテゴリ＝新規建の場合
    //対象建玉詳細.建日＜余力計算条件.営業日(T+0) - 当日建玉代金計上開始基準日の場合
    //・変動反映開始日に営業日(T+0)をセットする。
    public void testCalcReflectDay_C006()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_C006";
        log.entering(STR_METHOD_NAME);

        try
        {
            k = 2;
            Date l_datDate = WEB3DateUtility.getDate("20081028","yyyyMMdd");
            Date l_datOpen = WEB3DateUtility.getDate("20081010","yyyyMMdd");

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_OPEN_MARGIN;
            Date[] l_datBizDate = new Date[9];

            l_datBizDate[0] = WEB3DateUtility.getDate("20081211","yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20081212","yyyyMMdd");
            for(int i = 2;i < 9;i++)
            {
                l_datBizDate[i] = WEB3DateUtility.getDate("20081210","yyyyMMdd");
            }

            l_targetContractDetail.setOpenDate(l_datOpen);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPHistory l_tpHistory = new WEB3TPHistory();
            l_tpHistory.setCalcCondition(l_calcConditionForTest);
            l_tpHistory.setTargetContract(l_targetContract);
            l_calcConditionForTest.setBizDate(l_datBizDate);
            
            l_tpHistory.setTransactionCateg(l_transactionCateg);

            l_tpHistory.calcReflectDay(l_datDate);
            assertEquals("20081210",WEB3DateUtility.formatDate(l_tpHistory.getReflectStartDay(),"yyyyMMdd"));
            assertEquals("20081210",WEB3DateUtility.formatDate(l_tpHistory.getReflectEndDay(),"yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //引数がnullの時は、自オブジェクトに設定されてる受渡日を使う
    public void testCalcReflectDay_C007()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_C007";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datDate = null;
            Date l_datDelivery = WEB3DateUtility.getDate("20081028","yyyyMMdd");

            WEB3TPHistory l_tpHistory = new WEB3TPHistory();
            l_tpHistory.setDeliveryDate(l_datDelivery);

            Date l_datOpen = WEB3DateUtility.getDate("20081010","yyyyMMdd");

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_OPEN_MARGIN;
            Date[] l_datBizDate = new Date[9];

            for(int i = 0;i < 9;i++)
            {
                l_datBizDate[i] = WEB3DateUtility.getDate("20081210","yyyyMMdd");
            }

            l_targetContractDetail.setOpenDate(l_datOpen);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            l_tpHistory.setCalcCondition(l_calcConditionForTest);
            l_tpHistory.setTargetContract(l_targetContract);
            l_calcConditionForTest.setBizDate(l_datBizDate);
            
            l_tpHistory.setTransactionCateg(l_transactionCateg);

            l_tpHistory.calcReflectDay(l_datDate);
            assertEquals("20081210",WEB3DateUtility.formatDate(l_tpHistory.getReflectStartDay(),"yyyyMMdd"));
            assertEquals("20081210",WEB3DateUtility.formatDate(l_tpHistory.getReflectEndDay(),"yyyyMMdd"));   
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    public void testCalcReflectDay_C008()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay_C008";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_datDate = WEB3DateUtility.getDate("20081028","yyyyMMdd");
            Date l_datOpen = WEB3DateUtility.getDate("20081010","yyyyMMdd");

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            FinTransactionCateg l_transactionCateg = FinTransactionCateg.CASH_TRANSFER;


            l_targetContractDetail.setOpenDate(l_datOpen);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            WEB3TPHistory l_tpHistory = new WEB3TPHistory();
            l_tpHistory.setCalcCondition(l_calcConditionForTest);
            l_tpHistory.setTargetContract(l_targetContract);
            l_tpHistory.setTransactionCateg(l_transactionCateg);

            l_tpHistory.calcReflectDay(l_datDate);
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }


    class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
    {
        
        public String getInstBranCalcCondition(String l_strName)
        {
            String l_strInstBranCalcCondition = null;
            switch(k)
            {
                case 0:
                    l_strInstBranCalcCondition = WEB3TPContractAmountApplyDateDef.DEFAULT;
                    break;
                case 1:
                    l_strInstBranCalcCondition = WEB3TPContractAmountApplyDateDef.FROM_BIZ_DATE;
                    break;
                case 2:
                    l_strInstBranCalcCondition = WEB3TPContractAmountApplyDateDef.FROM_T2;
                    break;
            }
            return l_strInstBranCalcCondition;
        }
    }
}

    

@
