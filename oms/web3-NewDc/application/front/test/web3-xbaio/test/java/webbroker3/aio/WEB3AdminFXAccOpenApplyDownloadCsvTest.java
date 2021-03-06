head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXAccOpenApplyDownloadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : ()åa¤ Ø\[VVXeæñ
File Name           : ÇÒFXûÀJÝ\_E[hCSVÌeXgNX(WEB3AdminFXAccOpenApplyDownloadCsvTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/09/09 O (u) VKì¬
*/
package webbroker3.aio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (ÇÒFXûÀJÝ\_E[hCSV)<BR>
 * ÇÒFXûÀJÝ\_E[hCSVÌeXgNX<BR>
 *
 * @@author ünm
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyDownloadCsvTest extends TestBaseForMock
{
    /**
     * (OoÍ[eBeB)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFXAccOpenApplyDownloadCsvTest.class);

    /**
     * FXf[^§äT[rXImpl
     */
    WEB3AdminFXAccOpenApplyDownloadCsv l_csv = null;

    public WEB3AdminFXAccOpenApplyDownloadCsvTest(String arg0)
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

    public void testCreateColumnHeader_Case001()
    {
        final String STR_METHOD_NAME = "testCreateColumnHeader_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv = new WEB3AdminFXAccOpenApplyDownloadCsv();
            
            
            List l_lisColumnLabels = new ArrayList();
            l_lisColumnLabels.add("ÇÁÏXæª");
            l_lisColumnLabels.add("pÒR[h");
            l_lisColumnLabels.add("pÒ¼");
            l_lisColumnLabels.add("OCID");
            l_lisColumnLabels.add("OCpX[h");
            l_lisColumnLabels.add("­pX[h");
            l_lisColumnLabels.add("[AhXP");
            l_lisColumnLabels.add("[AhXQ");
            l_lisColumnLabels.add("©Èóõæª");
            l_lisColumnLabels.add("pÒ®«");
            l_lisColumnLabels.add("Ïû@@");
            l_lisColumnLabels.add("è¿æª");
            l_lisColumnLabels.add("XJbgæª");
            l_lisColumnLabels.add("æøÂ\æª");
            l_lisColumnLabels.add("dqðt³øú");
            l_lisColumnLabels.add("æøà¾mFú");
            l_lisColumnLabels.add("ñøÔ");
            l_lisColumnLabels.add("õl");
            l_lisColumnLabels.add("¤iR[hP");
            l_lisColumnLabels.add("­ãÀÊP");
            l_lisColumnLabels.add("¤iR[hQ");
            l_lisColumnLabels.add("­ãÀÊQ");
            l_lisColumnLabels.add("¤iR[hR");
            l_lisColumnLabels.add("­ãÀÊR");
            l_lisColumnLabels.add("¤iR[hS");
            l_lisColumnLabels.add("­ãÀÊS");
            l_lisColumnLabels.add("¤iR[hT");
            l_lisColumnLabels.add("­ãÀÊT");
            l_lisColumnLabels.add("¤iR[hU");
            l_lisColumnLabels.add("­ãÀÊU");
            l_lisColumnLabels.add("¤iR[hV");
            l_lisColumnLabels.add("­ãÀÊV");
            l_lisColumnLabels.add("¤iR[hW");
            l_lisColumnLabels.add("­ãÀÊW");
            l_lisColumnLabels.add("¤iR[hX");
            l_lisColumnLabels.add("­ãÀÊX");
            l_lisColumnLabels.add("¤iR[hPO");
            l_lisColumnLabels.add("­ãÀÊPO");
            l_lisColumnLabels.add("¤iR[hPP");
            l_lisColumnLabels.add("­ãÀÊPP");
            l_lisColumnLabels.add("¤iR[hPQ");
            l_lisColumnLabels.add("­ãÀÊPQ");
            l_lisColumnLabels.add("¤iR[hPR");
            l_lisColumnLabels.add("­ãÀÊPR");
            l_lisColumnLabels.add("¤iR[hPS");
            l_lisColumnLabels.add("­ãÀÊPS");
            l_lisColumnLabels.add("¤iR[hPT");
            l_lisColumnLabels.add("­ãÀÊPT");
            l_lisColumnLabels.add("¤iR[hPU");
            l_lisColumnLabels.add("­ãÀÊPU");
            l_lisColumnLabels.add("¤iR[hPV");
            l_lisColumnLabels.add("­ãÀÊPV");
            l_lisColumnLabels.add("¤iR[hPW");
            l_lisColumnLabels.add("­ãÀÊPW");
            l_lisColumnLabels.add("¤iR[hPX");
            l_lisColumnLabels.add("­ãÀÊPX");
            l_lisColumnLabels.add("¤iR[hQO");
            l_lisColumnLabels.add("­ãÀÊQO");
            l_lisColumnLabels.add("¤iR[hQP");
            l_lisColumnLabels.add("­ãÀÊQP");
            l_lisColumnLabels.add("¤iR[hQQ");
            l_lisColumnLabels.add("­ãÀÊQQ");
            l_lisColumnLabels.add("¤iR[hQR");
            l_lisColumnLabels.add("­ãÀÊQR");
            l_lisColumnLabels.add("¤iR[hQS");
            l_lisColumnLabels.add("­ãÀÊQS");
            l_lisColumnLabels.add("¤iR[hQT");
            l_lisColumnLabels.add("­ãÀÊQT");
            int l_strColumnType;
            DateFormat l_dateFormat = null;
            for (int i = 0; i < l_lisColumnLabels.size();i++)
            {
                String l_strLabel = (String) l_lisColumnLabels.get(i);
                WEB3GentradeCsvColumnModel l_model = l_csv.getColumnModel(l_strLabel);
                l_strColumnType = 0;
                l_dateFormat = null;
                assertEquals(i, l_model.getColumnNumber());
                if ("dqðt³øú".equals(l_strLabel) || "æøà¾mFú".equals(l_strLabel))
                {
                    l_strColumnType = 20;
                    l_dateFormat = new SimpleDateFormat("yyyyMMdd");
                }
                assertEquals(l_strColumnType, l_model.getColumnType());
                assertEquals(l_dateFormat, l_model.getDateFormat());
            }

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
