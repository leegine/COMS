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
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV�̃e�X�g�N���X(WEB3AdminFXAccOpenApplyDownloadCsvTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/09/09 ���O (���u) �V�K�쐬
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
 * (�Ǘ���FX�����J�ݐ\���_�E�����[�hCSV)<BR>
 * �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV�̃e�X�g�N���X<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyDownloadCsvTest extends TestBaseForMock
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFXAccOpenApplyDownloadCsvTest.class);

    /**
     * FX�f�[�^����T�[�r�XImpl
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
            l_lisColumnLabels.add("�ǉ��ύX�敪");
            l_lisColumnLabels.add("���p�҃R�[�h");
            l_lisColumnLabels.add("���p�Җ�");
            l_lisColumnLabels.add("���O�C��ID");
            l_lisColumnLabels.add("���O�C���p�X���[�h");
            l_lisColumnLabels.add("�����p�X���[�h");
            l_lisColumnLabels.add("���[���A�h���X�P");
            l_lisColumnLabels.add("���[���A�h���X�Q");
            l_lisColumnLabels.add("���Ȏ���敪");
            l_lisColumnLabels.add("���p�ґ���");
            l_lisColumnLabels.add("���ϕ��@@");
            l_lisColumnLabels.add("�萔���敪");
            l_lisColumnLabels.add("���X�J�b�g�敪");
            l_lisColumnLabels.add("����\�敪");
            l_lisColumnLabels.add("�d�q��t������");
            l_lisColumnLabels.add("����������m�F��");
            l_lisColumnLabels.add("������ԍ�");
            l_lisColumnLabels.add("���l");
            l_lisColumnLabels.add("���i�R�[�h�P");
            l_lisColumnLabels.add("����������ʂP");
            l_lisColumnLabels.add("���i�R�[�h�Q");
            l_lisColumnLabels.add("����������ʂQ");
            l_lisColumnLabels.add("���i�R�[�h�R");
            l_lisColumnLabels.add("����������ʂR");
            l_lisColumnLabels.add("���i�R�[�h�S");
            l_lisColumnLabels.add("����������ʂS");
            l_lisColumnLabels.add("���i�R�[�h�T");
            l_lisColumnLabels.add("����������ʂT");
            l_lisColumnLabels.add("���i�R�[�h�U");
            l_lisColumnLabels.add("����������ʂU");
            l_lisColumnLabels.add("���i�R�[�h�V");
            l_lisColumnLabels.add("����������ʂV");
            l_lisColumnLabels.add("���i�R�[�h�W");
            l_lisColumnLabels.add("����������ʂW");
            l_lisColumnLabels.add("���i�R�[�h�X");
            l_lisColumnLabels.add("����������ʂX");
            l_lisColumnLabels.add("���i�R�[�h�P�O");
            l_lisColumnLabels.add("����������ʂP�O");
            l_lisColumnLabels.add("���i�R�[�h�P�P");
            l_lisColumnLabels.add("����������ʂP�P");
            l_lisColumnLabels.add("���i�R�[�h�P�Q");
            l_lisColumnLabels.add("����������ʂP�Q");
            l_lisColumnLabels.add("���i�R�[�h�P�R");
            l_lisColumnLabels.add("����������ʂP�R");
            l_lisColumnLabels.add("���i�R�[�h�P�S");
            l_lisColumnLabels.add("����������ʂP�S");
            l_lisColumnLabels.add("���i�R�[�h�P�T");
            l_lisColumnLabels.add("����������ʂP�T");
            l_lisColumnLabels.add("���i�R�[�h�P�U");
            l_lisColumnLabels.add("����������ʂP�U");
            l_lisColumnLabels.add("���i�R�[�h�P�V");
            l_lisColumnLabels.add("����������ʂP�V");
            l_lisColumnLabels.add("���i�R�[�h�P�W");
            l_lisColumnLabels.add("����������ʂP�W");
            l_lisColumnLabels.add("���i�R�[�h�P�X");
            l_lisColumnLabels.add("����������ʂP�X");
            l_lisColumnLabels.add("���i�R�[�h�Q�O");
            l_lisColumnLabels.add("����������ʂQ�O");
            l_lisColumnLabels.add("���i�R�[�h�Q�P");
            l_lisColumnLabels.add("����������ʂQ�P");
            l_lisColumnLabels.add("���i�R�[�h�Q�Q");
            l_lisColumnLabels.add("����������ʂQ�Q");
            l_lisColumnLabels.add("���i�R�[�h�Q�R");
            l_lisColumnLabels.add("����������ʂQ�R");
            l_lisColumnLabels.add("���i�R�[�h�Q�S");
            l_lisColumnLabels.add("����������ʂQ�S");
            l_lisColumnLabels.add("���i�R�[�h�Q�T");
            l_lisColumnLabels.add("����������ʂQ�T");
            int l_strColumnType;
            DateFormat l_dateFormat = null;
            for (int i = 0; i < l_lisColumnLabels.size();i++)
            {
                String l_strLabel = (String) l_lisColumnLabels.get(i);
                WEB3GentradeCsvColumnModel l_model = l_csv.getColumnModel(l_strLabel);
                l_strColumnType = 0;
                l_dateFormat = null;
                assertEquals(i, l_model.getColumnNumber());
                if ("�d�q��t������".equals(l_strLabel) || "����������m�F��".equals(l_strLabel))
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
