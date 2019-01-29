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
            
            //���ʃR�[�h:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("999",row.getOrderRequestNumber());
            //�f�[�^�R�[�h:���E��c�d�q��t�E��������o�^�F�hGI843�h
            assertEquals("GI843",row.getRequestCode());
            //�،���ЃR�[�h:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("0D",row.getInstitutionCode());
            //���X�R�[�h:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("381",row.getBranchCode());
            
            //�ڋq�R�[�h:���X�R�[�h:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("01001",row.getAccountCode());
            
            //���҃R�[�h:���X�R�[�h:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals(null,row.getTraderCode());
            
            //���ʃR�[�h�i�����J�݌����q�j:ALL9 (9999999999999)
            assertEquals("9999999999999",row.getAccOpenRequestNumber());
            
            //�`�[�ʔ�:0
            assertEquals("0",row.getSerialNo());
            
            //����c���񍐏��@@���:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("x",row.getPosReportTermDiv());
            
            //����c���񍐏��@@�d�q��t�i�s�x�j:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("O",row.getPosReportCycleDiv());
            
            //����c���񍐏��@@�a���:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("D",row.getPosReportCertifDepoDiv());
            
            //����c���񍐏��@@�v�Z��:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("A",row.getPosReportAccStateDiv());
            
            //�d�q��t�@@����񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("E",row.getTradingEReportDiv());
            
            //�d�q��t�@@���M�^�p�񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("I",row.getInvEReportDiv());
            
            //�d�q��t�@@���z���E���ҋ�:���M�^�p�񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("R",row.getRefundEReportDiv());
            
            //�i�����j��������@@����:���M�^�p�񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("1",row.getEquityTaxDiv());
            
            //�i�����j��������@@����:���M�^�p�񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("2",row.getEquityTaxDivNext());
            
            //�i�����j��������@@�J�ݓ�:���M�^�p�񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("201011",row.getEquitySpAccOpenDat());
            
            //�i�M�p�j��������@@����:���M�^�p�񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("3",row.getMarginTaxDiv());
            
            //�i�M�p�j��������@@����:���M�^�p�񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("4",row.getMarginTaxDivNext());
            
            //�i�M�p�j��������@@�J�ݓ�:���M�^�p�񍐏�:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("201212",row.getMarginSpAccOpenDat());
            
            //����Ǘ�����:���E��c�d�q��t�E��������o�^�`�[�I�u�W�F�N�g�̓�������
            assertEquals("7",row.getSpMngAccOpenDiv());
            
            //�����敪:0
            assertEquals("0",row.getStatus());
            
            //���M����:null
            
        }
        catch(Exception ex)
        {
            fail();
        }

        

    }


}
@
