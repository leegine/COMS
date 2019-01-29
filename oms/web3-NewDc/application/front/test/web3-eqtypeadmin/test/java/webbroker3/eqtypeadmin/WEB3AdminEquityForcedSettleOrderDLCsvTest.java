head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleOrderDLCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : �I�O(���u) �d�l�ύX���f��No.211
*/
package webbroker3.eqtypeadmin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleOrderDLCsvTest extends TestBaseForMock
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderDLCsvTest.class);
    
    public WEB3AdminEquityForcedSettleOrderDLCsvTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        
        
        
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        
    }

    /**
     * create�L�[�w�b�_ 
     *
     */
    public void test_createKeyHeader_0001()
    {
        final String STR_METHOD_NAME = "test_createKeyHeader_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        String[] l_strKeyHeader = l_csv.getKeyHeader();
        assertEquals("1", "" + l_strKeyHeader.length);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * create�J�����w�b�_
     *
     */
    public void test_createColumnHeader_0001()
    {
        final String STR_METHOD_NAME = "test_createColumnHeader_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        WEB3GentradeCsvColumnModel[] l_columnModel = l_csv.getColumnHeader();
        
        //���X�R�[�h
        //���ڃ��x��
        assertEquals("���X", "" + l_columnModel[0].getColumnLabel());
        //�J�����ԍ�
        assertEquals("0", "" + l_columnModel[0].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[0].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[0].getDateFormat());
        
        //�ڋq�R�[�h 
        //���ڃ��x��
        assertEquals("�ڋq", "" + l_columnModel[1].getColumnLabel());
        //�J�����ԍ�
        assertEquals("1", "" + l_columnModel[1].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[1].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[1].getDateFormat());
        
        //�ڋq��
        //���ڃ��x��
        assertEquals("�ڋq��", "" + l_columnModel[2].getColumnLabel());
        //�J�����ԍ�
        assertEquals("2", "" + l_columnModel[2].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[2].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[2].getDateFormat());
        
        //�������ϗ��R
        //���ڃ��x��
        assertEquals("�������ϗ��R", "" + l_columnModel[3].getColumnLabel());
        //�J�����ԍ�
        assertEquals("3", "" + l_columnModel[3].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[3].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[3].getDateFormat());
        
        //�s�ꖼ
        //���ڃ��x��
        assertEquals("�s��", "" + l_columnModel[4].getColumnLabel());
        //�J�����ԍ�
        assertEquals("4", "" + l_columnModel[4].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[4].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[4].getDateFormat());
        
        //�����R�[�h
        //���ڃ��x��
        assertEquals("����", "" + l_columnModel[5].getColumnLabel());
        //�J�����ԍ�
        assertEquals("5", "" + l_columnModel[5].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[5].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[5].getDateFormat());
        
        //������
        //���ڃ��x��
        assertEquals("������", "" + l_columnModel[6].getColumnLabel());
        //�J�����ԍ�
        assertEquals("6", "" + l_columnModel[6].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[6].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[6].getDateFormat());
        
        //�ŋ敪
        //���ڃ��x��
        assertEquals("����", "" + l_columnModel[7].getColumnLabel());
        //�J�����ԍ�
        assertEquals("7", "" + l_columnModel[7].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[7].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[7].getDateFormat());
        
        //���敪
        //���ڃ��x��
        assertEquals("���敪", "" + l_columnModel[8].getColumnLabel());
        //�J�����ԍ�
        assertEquals("8", "" + l_columnModel[8].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[8].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[8].getDateFormat());
        
        //�ٍϋ敪
        //���ڃ��x��
        assertEquals("�ٍ�", "" + l_columnModel[9].getColumnLabel());
        //�J�����ԍ�
        assertEquals("9", "" + l_columnModel[9].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[9].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[9].getDateFormat());
        
        //����
        //���ڃ��x��
        assertEquals("����", "" + l_columnModel[10].getColumnLabel());
        //�J�����ԍ�
        assertEquals("10", "" + l_columnModel[10].getColumnNumber());
        //���ڌ^
        assertEquals("21", "" + l_columnModel[10].getColumnType());
        //���t�t�H�[�}�b�g
        assertEquals("yyyy/M/d", ((SimpleDateFormat)l_columnModel[10].getDateFormat()).toPattern());
        
        //���ϊ���
        //���ڃ��x��
        assertEquals("���ϊ���", "" + l_columnModel[11].getColumnLabel());
        //�J�����ԍ�
        assertEquals("11", "" + l_columnModel[11].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[11].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[11].getDateFormat());
        
        //������
        //���ڃ��x��
        assertEquals("������", "" + l_columnModel[12].getColumnLabel());
        //�J�����ԍ�
        assertEquals("12", "" + l_columnModel[12].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[12].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[12].getDateFormat());
        
        //���P��
        //���ڃ��x��
        assertEquals("���P��", "" + l_columnModel[13].getColumnLabel());
        //�J�����ԍ�
        assertEquals("13", "" + l_columnModel[13].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[13].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[13].getDateFormat());
        
        //�����
        //���ڃ��x��
        assertEquals("�����", "" + l_columnModel[14].getColumnLabel());
        //�J�����ԍ�
        assertEquals("14", "" + l_columnModel[14].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[14].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[14].getDateFormat());
        
        //�ۏ؋���
        //���ڃ��x��
        assertEquals("�ۏ؋��� (%)", "" + l_columnModel[15].getColumnLabel());
        //�J�����ԍ�
        assertEquals("15", "" + l_columnModel[15].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[15].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[15].getDateFormat());
        
        //�Ǐؔ�����
        //���ڃ��x��
        assertEquals("�Ǐؔ�����", "" + l_columnModel[16].getColumnLabel());
        //�J�����ԍ�
        assertEquals("16", "" + l_columnModel[16].getColumnNumber());
        //���ڌ^
        assertEquals("21", "" + l_columnModel[16].getColumnType());
        //���t�t�H�[�}�b�g
        assertEquals("yyyy/M/d", ((SimpleDateFormat)l_columnModel[16].getDateFormat()).toPattern());
        
        //�o�ߓ���
        //���ڃ��x��
        assertEquals("�o�ߓ���(��)", "" + l_columnModel[17].getColumnLabel());
        //�J�����ԍ�
        assertEquals("17", "" + l_columnModel[17].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[17].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[17].getDateFormat());
        
        //�쐬����
        //���ڃ��x��
        assertEquals("�쐬����", "" + l_columnModel[18].getColumnLabel());
        //�J�����ԍ�
        assertEquals("18", "" + l_columnModel[18].getColumnNumber());
        //���ڌ^
        assertEquals("21", "" + l_columnModel[18].getColumnType());
        //���t�t�H�[�}�b�g
        assertEquals("yyyy/M/d H:mm", ((SimpleDateFormat)l_columnModel[18].getDateFormat()).toPattern());
        
        //��������
        //���ڃ��x��
        assertEquals("��������", "" + l_columnModel[19].getColumnLabel());
        //�J�����ԍ�
        assertEquals("19", "" + l_columnModel[19].getColumnNumber());
        //���ڌ^
        assertEquals("21", "" + l_columnModel[19].getColumnType());
        //���t�t�H�[�}�b�g
        assertEquals("yyyy/M/d H:mm", ((SimpleDateFormat)l_columnModel[19].getDateFormat()).toPattern());
        
        //���F���
        //���ڃ��x��
        assertEquals("���F���", "" + l_columnModel[20].getColumnLabel());
        //�J�����ԍ�
        assertEquals("20", "" + l_columnModel[20].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[20].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[20].getDateFormat());
        
        //���F��
        //���ڃ��x��
        assertEquals("���F��", "" + l_columnModel[21].getColumnLabel());
        //�J�����ԍ�
        assertEquals("21", "" + l_columnModel[21].getColumnNumber());
        //���ڌ^
        assertEquals("0", "" + l_columnModel[21].getColumnType());
        //���t�t�H�[�}�b�g
        assertNull(l_columnModel[21].getDateFormat());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���X�R�[�h 
     *
     */
    public void test_setBranchCode_0001()
    {
        final String STR_METHOD_NAME = "test_setBranchCode_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_csv.setBranchCode(0, 33381);
            
            List list = (List)l_csv.l_rowValues.get(0);
            assertEquals("0", "" + ((Integer)list.get(0)).intValue());
            assertEquals("���X", "" + ((WEB3GentradeCsvColumnModel)list.get(1)).getColumnLabel());
            assertEquals("381", "" + (Object)list.get(2));
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * set�ڋq  
     *
     */
    public void test_setAccount_0001()
    {
        final String STR_METHOD_NAME = "test_setAccount_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_csv.setAccount(1, "23", 333812512246L);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("1", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�ڋq", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("23", "" + (Object)list0.get(2));
            
            
            List list1 = (List)l_csv.l_rowValues.get(1);
            assertEquals("1", "" + ((Integer)list1.get(0)).intValue());
            assertEquals("�ڋq��", "" + ((WEB3GentradeCsvColumnModel)list1.get(1)).getColumnLabel());
            assertEquals("�����@@�l�Y", "" + (Object)list1.get(2));
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�ڋq  
     * ����.�����h�c�ɊY������ڋq.get�ڋq�\����()
     * �擾�ł��Ȃ��ꍇ�́Anull���Z�b�g����B  
     */
    public void test_setAccount_0002()
    {
        final String STR_METHOD_NAME = "test_setAccount_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_csv.setAccount(1, "23", 333812512246L);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("1", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�ڋq", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("23", "" + (Object)list0.get(2));
            
            
            List list1 = (List)l_csv.l_rowValues.get(1);
            assertEquals("1", "" + ((Integer)list1.get(0)).intValue());
            assertEquals("�ڋq��", "" + ((WEB3GentradeCsvColumnModel)list1.get(1)).getColumnLabel());
            assertNull((Object)list1.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�������ϗ��R 
     * ����.�������ϗ��R�敪��"��������"�̏ꍇ
     * "���ϊ�������"
     */
    public void test_setForcedSettleReason_0001()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "0");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�������ϗ��R", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("���ϊ�������", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�������ϗ��R 
     * ����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�y�x�j"�̏ꍇ
     * 30%����7���ȏ�
     */
    public void test_setForcedSettleReason_0002()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "1");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�������ϗ��R", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("30%����7���ȏ�", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�������ϗ��R 
     * ����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�d�x�j"�̏ꍇ
     * 30%����7���ȏ�
     */
    public void test_setForcedSettleReason_0003()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "2");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�������ϗ��R", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("30%����7���ȏ�", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�������ϗ��R 
     * ����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j"�̏ꍇ
     * 20%�����������
     */
    public void test_setForcedSettleReason_0004()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "4");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�������ϗ��R", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("20%�����������", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�������ϗ��R 
     * ����.�������ϗ��R�敪��"�ۏ؋��ێ�������i��ԁj"�̏ꍇ
     * 20%�����������
     */
    public void test_setForcedSettleReason_0005()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "3");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�������ϗ��R", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("20%�����������", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�������ϗ��R 
     * ����.�������ϗ��R�敪��"�蓮��������"�̏ꍇ
     * �蓮��������
     */
    public void test_setForcedSettleReason_0006()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0006()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "9");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�������ϗ��R", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�蓮��������", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�s�ꖼ  
     *
     */
    public void test_setMarketName_0001()
    {
        final String STR_METHOD_NAME = "test_setMarketName_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            l_csv.setMarketName(3, 3303L);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("3", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�s��", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�V���K�|�[��", "" + (Object)list0.get(2));
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���� 
     *
     */
    public void test_setProduct_0001()
    {
        final String STR_METHOD_NAME = "test_setProduct_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setStandardName("111111111");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            l_csv.setProduct(4, "dadsadsa", 3304148080000L);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("4", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("����", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("dads", "" + (Object)list0.get(2));
            
            
            List list1 = (List)l_csv.l_rowValues.get(1);
            assertEquals("4", "" + ((Integer)list1.get(0)).intValue());
            assertEquals("������", "" + ((WEB3GentradeCsvColumnModel)list1.get(1)).getColumnLabel());
            assertEquals("111111111", "" + (Object)list1.get(2));
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���� 
     * ����.����ID�ɊY�����銔������.������
     * �擾�ł��Ȃ��ꍇ�́Anull���Z�b�g����B 
     */
    public void test_setProduct_0002()
    {
        final String STR_METHOD_NAME = "test_setProduct_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            
            l_csv.setProduct(4, "dadsadsa", 3304148080000L);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("4", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("����", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("dads", "" + (Object)list0.get(2));
            
            
            List list1 = (List)l_csv.l_rowValues.get(1);
            assertEquals("4", "" + ((Integer)list1.get(0)).intValue());
            assertEquals("������", "" + ((WEB3GentradeCsvColumnModel)list1.get(1)).getColumnLabel());
            assertNull((Object)list1.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * set�ŋ敪 
     * ����.�ŋ敪��TaxTypeEnum."���"�̏ꍇ
     * "���"
     */
    public void test_setTaxType_0001()
    {
        final String STR_METHOD_NAME = "test_setTaxType_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setTaxType(5, TaxTypeEnum.NORMAL);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("5", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("����", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("���", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�ŋ敪 
     * ����.�ŋ敪��TaxTypeEnum."����"�̏ꍇ
     * "����"
     */
    public void test_setTaxType_0002()
    {
        final String STR_METHOD_NAME = "test_setTaxType_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setTaxType(5, TaxTypeEnum.SPECIAL);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("5", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("����", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("����", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�ŋ敪 
     * ����.�ŋ敪��TaxTypeEnum."������������򒥎�"�̏ꍇ
     * "����"
     */
    public void test_setTaxType_0003()
    {
        final String STR_METHOD_NAME = "test_setTaxType_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setTaxType(5, TaxTypeEnum.SPECIAL_WITHHOLD);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("5", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("����", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("����", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�ŋ敪 
     * [�ȊO�̏ꍇ]
     * null���Z�b�g����
     */
    public void test_setTaxType_0004()
    {
        final String STR_METHOD_NAME = "test_setTaxType_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setTaxType(5, TaxTypeEnum.STOCK_OPTION);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("5", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("����", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertNull((Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���敪 
     * ����.���敪��ContractTypeEnum."����"�̏ꍇ
     * "�V�K��"
     */
    public void test_setContractType_0001()
    {
        final String STR_METHOD_NAME = "test_setContractType_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setContractType(6, ContractTypeEnum.LONG);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("6", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���敪", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�V�K��", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���敪 
     * ����.���敪��ContractTypeEnum."����"�̏ꍇ
     * "�V�K��"
     */
    public void test_setContractType_0002()
    {
        final String STR_METHOD_NAME = "test_setContractType_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setContractType(6, ContractTypeEnum.SHORT);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("6", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���敪", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�V�K��", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�ٍϋ敪 
     * ����.�ٍϋ敪��"1�F���x�M�p"�̏ꍇ
     * "���x�M�p"
     */
    public void test_setRepaymentDiv_0001()
    {
        final String STR_METHOD_NAME = "test_setRepaymentDiv_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setRepaymentDiv(7, "1");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("7", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�ٍ�", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("���x�M�p", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�ٍϋ敪 
     * ����.�ٍϋ敪��"2�F��ʐM�p"�̏ꍇ
     * "��ʐM�p"
     */
    public void test_setRepaymentDiv_0002()
    {
        final String STR_METHOD_NAME = "test_setRepaymentDiv_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setRepaymentDiv(7, "2");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("7", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�ٍ�", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("��ʐM�p", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���� 
     * 
     */
    public void test_setOpenDate_0001()
    {
        final String STR_METHOD_NAME = "test_setOpenDate_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setOpenDate(8, Calendar.getInstance().getTime());
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("8", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("����", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), 
                    (Date)list0.get(2)));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���ϊ��� 
     * ����.���ϊ�����"9999/12/31"�̏ꍇ
     * "����"
     */
    public void test_setCloseDate_0001()
    {
        final String STR_METHOD_NAME = "test_setCloseDate_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setCloseDate(9, WEB3DateUtility.getDate("9999/12/31", "yyyy/MM/dd"));
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("9", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���ϊ���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("����", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���ϊ��� 
     * �ȊO�̏ꍇ
     * "yyyy/M/d"
     */
    public void test_setCloseDate_0002()
    {
        final String STR_METHOD_NAME = "test_setCloseDate_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setCloseDate(9, Calendar.getInstance().getTime());
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("9", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���ϊ���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(),"yyyy/M/d"), (String)list0.get(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set������ 
     */
    public void test_setContractQuantity_0001()
    {
        final String STR_METHOD_NAME = "test_setContractQuantity_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setContractQuantity(10, "123");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("10", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("������", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("123", (String)list0.get(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���P��  
     */
    public void test_setContractPrice_0001()
    {
        final String STR_METHOD_NAME = "test_setContractPrice_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setContractPrice(11, "255");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("11", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���P��", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("255", (String)list0.get(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�����
     */
    public void test_setContractExecPrice_0001()
    {
        final String STR_METHOD_NAME = "test_setContractExecPrice_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setContractExecPrice(12, "12313");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("12", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�����", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("12313", (String)list0.get(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�ۏ؋���
     */
    public void test_setMarginDepositRate_0001()
    {
        final String STR_METHOD_NAME = "test_setMarginDepositRate_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setMarginDepositRate(13, "258");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("13", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�ۏ؋��� (%)", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("258", (String)list0.get(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�Ǐؔ�����  
     * 
     */
    public void test_setAdditionalMarginDate_0001()
    {
        final String STR_METHOD_NAME = "test_setAdditionalMarginDate_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setAdditionalMarginDate(14, Calendar.getInstance().getTime());
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("14", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�Ǐؔ�����", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), 
                    (Date)list0.get(2)));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�o�ߓ���  
     * ����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j"�̏ꍇ
     * "�Ǐؖ���"
     */
    public void test_setMarginAccruedDays_0001()
    {
        final String STR_METHOD_NAME = "test_setMarginAccruedDays_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setMarginAccruedDays(15, "11232", "4");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�o�ߓ���(��)", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�Ǐؖ���", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�o�ߓ���  
     * ����.�������ϗ��R�敪��"�ۏ؋��ێ�������i��ԁj"�̏ꍇ
     * "�Ǐؖ���"
     */
    public void test_setMarginAccruedDays_0002()
    {
        final String STR_METHOD_NAME = "test_setMarginAccruedDays_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setMarginAccruedDays(15, "11232", "3");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�o�ߓ���(��)", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�Ǐؖ���", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�o�ߓ���  
     * �ȊO�̏ꍇ
     * ����.�o�ߓ��� 
     */
    public void test_setMarginAccruedDays_0003()
    {
        final String STR_METHOD_NAME = "test_setMarginAccruedDays_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setMarginAccruedDays(15, "11232", "0");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�o�ߓ���(��)", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("11232", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�쐬����
     *
     */
    public void test_setCreatedTimestamp_0001()
    {
        final String STR_METHOD_NAME = "test_setCreatedTimestamp_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setCreatedTimestamp(16, Calendar.getInstance().getTime());
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("16", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("�쐬����", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), 
                    (Date)list0.get(2)));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set��������
     *
     */
    public void test_setProcessTime_0001()
    {
        final String STR_METHOD_NAME = "test_setProcessTime_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setProcessTime(17, Calendar.getInstance().getTime());
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("17", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("��������", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), 
                    (Date)list0.get(2)));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���F��� 
     * ����.���F��ԋ敪��"0�F�����F"�̏ꍇ
     * "�����F" 
     */
    public void test_setApproveStatus_0001()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "0", "4", null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���F���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�����F", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���F��� 
     * ����.���F��ԋ敪��"1�F���F��"�̏ꍇ
     * "���F��" 
     */
    public void test_setApproveStatus_0002()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "1", "4",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���F���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("���F��", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���F��� 
     * ����.���F��ԋ敪��"2�F�񏳔F"�̏ꍇ
     * "�۔F��" 
     */
    public void test_setApproveStatus_0003()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "2", "4",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���F���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�۔F��", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���F��� 
     * ����.���F��ԋ敪��"9�F�G���["�̏ꍇ
     * ����.�����G���[���R�R�[�h��"�����c���s���G���["�̏ꍇ
     * "�����c���s���G���["  
     */
    public void test_setApproveStatus_0004()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "9", "0005",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���F���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�����c���s���G���[", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���F��� 
     * ����.���F��ԋ敪��"9�F�G���["�̏ꍇ
     * ����.�����G���[���R�R�[�h��"������~�����G���["�̏ꍇ
     * "������~�����G���["  
     */
    public void test_setApproveStatus_0005()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "9", "0006",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���F���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("������~�����G���[", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���F��� 
     * ����.���F��ԋ敪��"9�F�G���["�̏ꍇ
     * ����.�����G���[���R�R�[�h��"���ϊ��������σG���["�̏ꍇ
     * "���ϊ��������σG���["  
     */
    public void test_setApproveStatus_0006()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0006()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "9", "0016",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���F���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("���ϊ��������σG���[", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���F��� 
     * ����.���F��ԋ敪��"9�F�G���["�̏ꍇ
     * ����.�����G���[���R�R�[�h��"�����E���n�����o�^�σG���["�̏ꍇ
     * "�����E���n�����o�^�σG���["  
     */
    public void test_setApproveStatus_0007()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0007()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "9", "0017",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���F���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�����E���n�����o�^�σG���[", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set���F��� 
     * ����.���F��ԋ敪��"9�F�G���["�̏ꍇ
     * ����.�����G���[���R�R�[�h��"���̑��G���["�̏ꍇ
     * "���̑��G���["  
     */
    public void test_setApproveStatus_0008()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0008()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "9", "9001",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���F���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("���̑��G���[", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * set���F��� 
     * ����.�������ϗ��R�敪��"�蓮��������"�̏ꍇ
     * "�蓮���F��" 
     */
    public void test_setApproveStatus_0009()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0009()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "1", "4","9");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���F���", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("�蓮���F��", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * set���F�� 
     * ����.���F��ԋ敪��"0�F�����F"�̏ꍇ
     * "�����F" 
     */
    public void test_setApprover_0001()
    {
        final String STR_METHOD_NAME = "test_setApprover_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApprover(20, "256");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("20", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("���F��", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("256", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3AdminEquityForcedSettleOrderDLCsvForTest extends WEB3AdminEquityForcedSettleOrderDLCsv
    {        
        public List l_rowValues = new ArrayList();
        
        public String[] getKeyHeader()
        {
            return this.keyHeader;
        }

        public WEB3GentradeCsvColumnModel[] getColumnHeader()
        {
            return this.columnHeader;
        }
        
        public void setValue(
            int l_intLineNumber,
            WEB3GentradeCsvColumnModel l_csvColumnModel,
            Object l_objValue)
        {         
            List list = new ArrayList();
            list.add(new Integer(l_intLineNumber));
            list.add(l_csvColumnModel);
            list.add(l_objValue);
            
            l_rowValues.add(list);
        }
    }
}
@
