head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.14.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (��)��a�����r�W�l�X�E�C�m�x�[�V����
 File Name           : WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest.java
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/02 �����C(�k�����u) �V�K�쐬
 */
package webbroker3.ifoadmin.message;

import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest extends TestBaseForMock
{

    public WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest.class);
    
    public void testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0001()
    {
        final String STR_METHOD_NAME =
            "testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0001()";
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0002()
    {
        final String STR_METHOD_NAME =
            "testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0002()";
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0003()
    {
        final String STR_METHOD_NAME =
            "testWEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorCase0003()";
        
        String strAscDesc = "B";
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
        }
        catch (IllegalArgumentException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            assertEquals("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B",
                l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0001()
    {
        final String STR_METHOD_NAME = "testCompareCase0001()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(-1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0002()
    {
        final String STR_METHOD_NAME = "testCompareCase0002()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(0, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0003()
    {
        final String STR_METHOD_NAME = "testCompareCase0003()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0004()
    {
        final String STR_METHOD_NAME = "testCompareCase0004()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0005()
    {
        final String STR_METHOD_NAME = "testCompareCase0005()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(0, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0006()
    {
        final String STR_METHOD_NAME = "testCompareCase0006()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101030","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(-1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0007()
    {
        final String STR_METHOD_NAME = "testCompareCase0007()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(null);
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(-1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0008()
    {
        final String STR_METHOD_NAME = "testCompareCase0008()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(null);
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(null);
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(0, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0009()
    {
        final String STR_METHOD_NAME = "testCompareCase0009()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.ASC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(null);
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0010()
    {
        final String STR_METHOD_NAME = "testCompareCase0010()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(null);
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0011()
    {
        final String STR_METHOD_NAME = "testCompareCase0011()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(null);
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(null);
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(0, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0012()
    {
        final String STR_METHOD_NAME = "testCompareCase0012()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎�M����
        l_params1.setReceivedDateTime(WEB3DateUtility.getDate("20101031","yyyyMMdd"));
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(null);
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
            
            assertEquals(-1, l_intResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0013()
    {
        final String STR_METHOD_NAME = "testCompareCase0013()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        IfoProductParams l_params1 = new IfoProductParams();
        
        HostFotypeOrderAllParams l_params2 = new HostFotypeOrderAllParams();
        //����2�̎󒍓���
        l_params2.setReceivedDateTime(null);
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
        }
        catch (IllegalArgumentException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            assertEquals("�p�����[�^�̗ތ^���s���A�Y������'HostFotypeOrderAllRow'�ތ^�B",
                l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCompareCase0014()
    {
        final String STR_METHOD_NAME = "testCompareCase0014()";
        log.entering(STR_METHOD_NAME);
        
        String strAscDesc = WEB3AscDescDef.DESC;
        
        HostFotypeOrderAllParams l_params1 = new HostFotypeOrderAllParams();
        //����1�̎󒍓���
        l_params1.setReceivedDateTime(null);
        
        IfoProductParams l_params2 = new IfoProductParams();
        
        try
        {
            WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator l_comparator =
                new WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparator(strAscDesc);
            
            int l_intResult = l_comparator.compare(l_params1, l_params2);
        }
        catch (IllegalArgumentException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            assertEquals("�p�����[�^�̗ތ^���s���A�Y������'HostFotypeOrderAllRow'�ތ^�B",
                l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
