head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.31.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9c24957ec8;
filename	WEB3AccInfoAccopenConditionRegAcceptVoucherTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.mock.TestBaseForMock;

public class WEB3AccInfoAccopenConditionRegAcceptVoucherTest extends TestBaseForMock{

    public WEB3AccInfoAccopenConditionRegAcceptVoucherTest(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    public void testSave()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        Date d = new Date();
        d.setYear(2012);
        d.setMonth(12);
        d.setDate(12);
       
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(d.getTime()));

        HostConditionRegVoucherParams l_hostConditionRegVoucherParams
        = new HostConditionRegVoucherParams();
        l_hostConditionRegVoucherParams.setOrderRequestNumber("r_request");
        l_hostConditionRegVoucherParams.setRequestCode("GG119");
        l_hostConditionRegVoucherParams.setInstitutionCode("0D");
        l_hostConditionRegVoucherParams.setBranchCode("381");
        l_hostConditionRegVoucherParams.setAccountCode("01001");
        l_hostConditionRegVoucherParams.setTraderCode(null);
        l_hostConditionRegVoucherParams.setAccOpenRequestNumber("8888888888888");
        l_hostConditionRegVoucherParams.setSerialNo("9");
        l_hostConditionRegVoucherParams.setPosReportTermDiv("x");
        l_hostConditionRegVoucherParams.setPosReportCycleDiv("O");
        l_hostConditionRegVoucherParams.setPosReportCertifDepoDiv("D");
        l_hostConditionRegVoucherParams.setPosReportAccStateDiv("A");
        l_hostConditionRegVoucherParams.setTradingEReportDiv("E");
        l_hostConditionRegVoucherParams.setInvEReportDiv("I");
        l_hostConditionRegVoucherParams.setRefundEReportDiv("R");
        l_hostConditionRegVoucherParams.setEquityTaxDiv("1");
        l_hostConditionRegVoucherParams.setEquityTaxDivNext("2");
        l_hostConditionRegVoucherParams.setEquitySpAccOpenDat("201011");
        l_hostConditionRegVoucherParams.setMarginTaxDiv("3");
        l_hostConditionRegVoucherParams.setMarginTaxDivNext("4");
        l_hostConditionRegVoucherParams.setMarginSpAccOpenDat("201212");
        l_hostConditionRegVoucherParams.setSpMngAccOpenDiv("7");
        l_hostConditionRegVoucherParams.setStatus("1");
        l_hostConditionRegVoucherParams.setSendTimestamp(GtlUtils.getSystemTimestamp());
        l_hostConditionRegVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_hostConditionRegVoucherParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostConditionRegVoucherRow.TYPE);
            
            WEB3AccInfoAccopenConditionRegAcceptVoucher l_ver =
                new WEB3AccInfoAccopenConditionRegAcceptVoucher(l_hostConditionRegVoucherParams,"999");
            l_ver.saveHostConditionRegVoucherParams();
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_rows  = l_queryProcessor.doFindAllQuery(HostConditionRegVoucherRow.TYPE);
            HostConditionRegVoucherRow row = (HostConditionRegVoucherRow)l_rows.get(0);
            
            //識別コード:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("999",row.getOrderRequestNumber());
            //データコード:取報・取残電子交付・特定口座登録：”GI843”
            assertEquals("GI843",row.getRequestCode());
            //証券会社コード:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("0D",row.getInstitutionCode());
            //部店コード:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("381",row.getBranchCode());
            
            //顧客コード:部店コード:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("01001",row.getAccountCode());
            
            //扱者コード:部店コード:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals(null,row.getTraderCode());
            
            //識別コード（口座開設見込客）:ALL9 (9999999999999)
            assertEquals("9999999999999",row.getAccOpenRequestNumber());
            
            //伝票通番:0
            assertEquals("0",row.getSerialNo());
            
            //取引残高報告書　@定期:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("x",row.getPosReportTermDiv());
            
            //取引残高報告書　@電子交付（都度）:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("O",row.getPosReportCycleDiv());
            
            //取引残高報告書　@預り証:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("D",row.getPosReportCertifDepoDiv());
            
            //取引残高報告書　@計算書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("A",row.getPosReportAccStateDiv());
            
            //電子交付　@取引報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("E",row.getTradingEReportDiv());
            
            //電子交付　@投信運用報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("I",row.getInvEReportDiv());
            
            //電子交付　@分配金・償還金:投信運用報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("R",row.getRefundEReportDiv());
            
            //（現物）特定口座　@今回:投信運用報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("1",row.getEquityTaxDiv());
            
            //（現物）特定口座　@次回:投信運用報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("2",row.getEquityTaxDivNext());
            
            //（現物）特定口座　@開設日:投信運用報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("201011",row.getEquitySpAccOpenDat());
            
            //（信用）特定口座　@今回:投信運用報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("3",row.getMarginTaxDiv());
            
            //（信用）特定口座　@次回:投信運用報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("4",row.getMarginTaxDivNext());
            
            //（信用）特定口座　@開設日:投信運用報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("201212",row.getMarginSpAccOpenDat());
            
            //特定管理口座:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            assertEquals("7",row.getSpMngAccOpenDiv());
            
            //処理区分:0
            assertEquals("0",row.getStatus());
            
            //送信日時:null
            
        }
        catch(Exception ex)
        {
            fail();
        }

        

    }


}
@
