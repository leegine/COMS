head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXAccOpenUploadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV�e�X�g(WEB3AdminFXAccOpenUploadCsvTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/09 ���m�a (���u) �V�K�쐬
*/

package webbroker3.aio;

import java.util.ArrayList;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�Ǘ���FX�����J�݃A�b�v���[�hCSV�e�X�g�j<BR>
 *
 * @@author ���m�a
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadCsvTest extends TestBaseForMock {

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioListDownloadCSVTest.class);

	public WEB3AdminFXAccOpenUploadCsvTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	private int count = 0;

    /**
     * validate���׍s(All����)<BR>
     */
	public void testValidateDetailsLine_Case001()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();
        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("1,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("2008050112,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try {

			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);

		} catch (WEB3BaseException l_ex) {

			log.error(l_ex.getMessage());
			fail();
		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());

			fail();
		}
		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate���׍s(���p�Җ��`�F�b�N)<BR>
     */
	public void testValidateDetailsLine_Case002()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();
        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf" +
        		"ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf" +
        		"ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("12,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("2008050112,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try {

			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);

            fail();
		} catch (WEB3BaseException l_ex) {

			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02369,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());

			fail();
		}
		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate���׍s(9���p�ґ����̃`�F�b�N)<BR>
     */
	public void testValidateDetailsLine_Case003()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();
        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("12,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("2008050112,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);

            fail();
		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03111,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate���׍s(���ϕ��@@�̃`�F�b�N)<BR>
     */
	public void testValidateDetailsLine_Case004()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("1,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("12,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("2008050112,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try 
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);

            fail();
		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03112,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}	

    /**
     * validate���׍s(�d�q��t�������̃`�F�b�N)<BR>
     */
	public void testValidateDetailsLine_Case005()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("1,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("200805019,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("2008050112,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();
		} catch (WEB3BaseException l_ex) {

			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03113,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}


    /**
     * validate���׍s(15 ����������m�F���̃`�F�b�N)<BR>
     */
	public void testValidateDetailsLine_Case006()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N            
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("1,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("200805019,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("2008050111,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try 
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();

		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03114,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate���׍s(16 ������ԍ��̃`�F�b�N)<BR>
     */
	public void testValidateDetailsLine_Case007()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("1,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("j2008050112,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();

		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03115,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}
		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate���׍s(33 �����������8�̃`�F�b�N)<BR>
     */
	public void testValidateDetailsLine_Case008()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("1,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("200805011,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("1235562145645644,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();
		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02380,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate���׍s(33 �����������8�̃`�F�b�N)<BR>
     */
	public void testValidateDetailsLine_Case0011()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("1,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("200805011,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("dsfsde223,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try
        {

			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();

		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02379,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate���׍s(32 ���i�R�[�h8�̃`�F�b�N)<BR>
     */
	public void testValidateDetailsLine_Case0010()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("1,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("200805011,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff888,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();
		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02378,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}


    /**
     * validate���׍s(33 �����������8�̃`�F�b�N)<BR>
     */
	public void testValidateDetailsLine_Case009()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("1,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("200805011,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("dsfsde,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try
        {

			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();

		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02379,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}

    /**
     * create�J�����w�b�_<BR>
     */
	public void testCreateColumnHeader_Case001()
	{
        final String STR_METHOD_NAME = "testCreateColumnHeader_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFXAccOpenUploadCsv l_csv = null;
        List l_lisLabels = new ArrayList();
        try {

        l_csv = new WEB3AdminFXAccOpenUploadCsv();

        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���p�҃R�[�h���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_CODE_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���p�Җ����x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_NAME_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���O�C��ID���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOGINID_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���O�C���p�X���[�h���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOGIN_PASSWORD_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.�����p�X���[�h���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_PASSWORD_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���[���A�h���X�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS1_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���[���A�h���X�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS2_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���Ȏ���敪���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.SELF_TRUSTDIV_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���p�ґ������x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_ATTRIBUTE_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���ϕ��@@���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.SETTLEMENT_METHOD_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.�萔���敪���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.COMMISSIONDIV_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���X�J�b�g�敪���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOSSCUTDIV_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����\�敪���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.TRADINGDIV_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.�d�q��t���������x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ELECTRON_GRANT_PERMISSION_DAY_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������m�F�����x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.TRADE_OPERATING_MANUAL_CONFIRMATION_DAY_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.������ԍ����x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.AGREEMENT_BOOK_NUMBER_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���l���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.REMARK_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE1_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER1_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE2_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER2_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�R���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE3_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂR���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER3_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�S���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE4_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂS���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER4_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�T���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE5_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂT���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER5_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�U���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE6_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂU���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER6_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�V���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE7_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂV���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER7_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�W���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE8_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂW���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER8_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�X���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE9_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂX���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER9_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�O���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE10_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�O���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER10_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE11_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER11_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE12_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER12_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�R���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE13_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�R���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER13_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�S���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE14_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�S���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER14_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�T���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE15_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�T���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER15_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�U���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE16_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�U���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER16_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�V���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE17_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�V���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER17_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�W���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE18_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�W���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER18_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�P�X���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE19_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂP�X���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER19_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�O���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE20_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�O���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER20_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE21_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�P���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER21_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE22_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�Q���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER22_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�R���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE23_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�R���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER23_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�S���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE24_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�S���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER24_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.���i�R�[�h�Q�T���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE25_LABEL);
        //�Ǘ���FX�����J�݃A�b�v���[�hCSV.����������ʂQ�T���x��
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER25_LABEL);

        for (int i = 0; i < l_lisLabels.size();i++)
        {
            String l_strLabel = (String) l_lisLabels.get(i);
            WEB3GentradeCsvColumnModel l_model = l_csv.getColumnModel(l_strLabel);
            String l_strNewLabel = l_model.getColumnLabel();

            assertEquals(i, l_model.getColumnNumber());
            assertEquals(l_strLabel,l_strNewLabel);
        }

    }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
 
	}

	public String getRowDigit()
	{
	  StringBuffer  l_sbRow = new StringBuffer();

	  //�P�j�@@���p�҃R�[�h�̃`�F�b�N
	  l_sbRow.append("12345678,");

     //�Q�j�@@���p�Җ��`�F�b�N
     l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");

      //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
      l_sbRow.append("12456432,");
      //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
      l_sbRow.append("afdff1652,");
      //�T�j�@@�����p�X���[�h�̃`�F�b�N
      l_sbRow.append("afdffg652,");
      //�U�j�@@���[���A�h���X�P�̃`�F�b�N
      l_sbRow.append("fgsdgddfsdf453,");
      //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
      l_sbRow.append("sdfsfsdff5453,");
      //�W�j�@@���Ȏ���敪�̃`�F�b�N
      l_sbRow.append("1,");
      //�X�j�@@���p�ґ����̃`�F�b�N     
      l_sbRow.append("1,");
      //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
      l_sbRow.append("1,");
      //�P�P�j�@@�萔���敪�̃`�F�b�N
      l_sbRow.append("a1,");
      //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
      l_sbRow.append("a1,");
      //�P�R�j�@@����\�敪�̃`�F�b�N
      l_sbRow.append("1,");
      //�P�S�j�@@�d�q��t�������̃`�F�b�N
      l_sbRow.append("20080501,");
      //�P�T�j�@@����������m�F���̃`�F�b�N
      l_sbRow.append("20080501,");
      //�P�U�j�@@������ԍ��̃`�F�b�N
      l_sbRow.append("200805011,");
      //�P�V�j�@@���l�̃`�F�b�N
      l_sbRow.append("afjsidfer,");
      //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //�P�X�j�@@����������ʂP�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //20�j�@@���i�R�[�h2�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //21�j�@@�����������2�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //22�j�@@���i�R�[�h3�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //23�j�@@�����������3�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //24�j�@@���i�R�[�h4�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //25�j�@@�����������4�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //26�j�@@���i�R�[�h5�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //27�j�@@�����������5�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //28�j�@@���i�R�[�h6�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //29�j�@@�����������6�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //30�j�@@���i�R�[�h7�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //31�j�@@�����������7�̃`�F�b�N 
      l_sbRow.append("12355621,");

      //32�j�@@���i�R�[�h8�̃`�F�b�N   
      l_sbRow.append("3453532,");

      if(count == 0 )
      {
          //33�j�@@�����������8�̃`�F�b�N 
          l_sbRow.append("fgfdgs,");
      }
      else
      {
          //33�j�@@�����������8�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }

      //34�j�@@���i�R�[�h9�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 1)
      {
          //35�j�@@�����������9�̃`�F�b�N 
          l_sbRow.append("sdfsdf,");
      }
      else
      {
          //35�j�@@�����������9�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }

      //36�j�@@���i�R�[�h10�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 2)
      {
          //37�j�@@�����������10�̃`�F�b�N 
          l_sbRow.append("fgfdg,");
      }
      else
      {
          //37�j�@@�����������10�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }

      //38�j�@@���i�R�[�h11�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 3)
      {
          //39�j�@@�����������11�̃`�F�b�N 
          l_sbRow.append("dfsfs3,");
      }
      else
      {
          //39�j�@@�����������11�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }

      //40�j�@@���i�R�[�h12�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 4)
      {
          //41�j�@@�����������12�̃`�F�b�N 
          l_sbRow.append("ggeg23,");
      }
      else
      {
          //41�j�@@�����������12�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }

      //42�j�@@���i�R�[�h13�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 5)
      {
          //43�j�@@�����������13�̃`�F�b�N 
          l_sbRow.append("dfgd345,");
      }
      else
      {
          //43�j�@@�����������13�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }

      //44�j�@@���i�R�[�h14�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 6)
      {
          //45�j�@@�����������14�̃`�F�b�N 
          l_sbRow.append("sfsfdd2354,");
      }
      else
      {
          //45�j�@@�����������14�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      //46�j�@@���i�R�[�h15�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 7)
      {
          //47�j�@@�����������15�̃`�F�b�N 
          l_sbRow.append("dfsfsdf34,");
      }
      else
      {
          //47�j�@@�����������15�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      //48�j�@@���i�R�[�h16�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 8)
      {
          //49�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("sdfsf345,");
      }
      else
      {
          //49�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
       //50�j�@@���i�R�[�h16�̃`�F�b�N   
       l_sbRow.append("435345,");

      if(count == 9)
      {
          //51�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("dfgdfg344,");
      }
      else
      {
          //51�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      //52�j�@@���i�R�[�h16�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 10)
      {
          //53�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("sfsdf344,");
      }
      else
      {
          //53�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      //54�j�@@���i�R�[�h16�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 11)
      {
          //55�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("gdgdf345,");
      }
      else
      {
          //55�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }

      //56�j�@@���i�R�[�h16�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 12)
      {
          //57�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("dsfsfs34,");
      }
      else
      {
          //57�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }

      //58�j�@@���i�R�[�h16�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 13)
      {
          //59�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("fgsdf355,");
      }
      else
      {
          //59�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }

      //60�j�@@���i�R�[�h16�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 14)
      {
          //61�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("fdgdgd34,");
      }
      else
      {
          //61�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      //62�j�@@���i�R�[�h16�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 15)
      {
          //63�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("dgdsfgdf,");
      }
      else
      {
          //63�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }

      //64�j�@@���i�R�[�h16�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 16)
      {
          //65�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("ffgdfgsd,");
      }
      else
      {
          //65�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }      

      //66�j�@@���i�R�[�h16�̃`�F�b�N   
      l_sbRow.append("435345,");

      if(count == 17)
      {
          //67�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("gdsfgsdg45");
      }
      else
      {
          //67�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253");
      }
          return l_sbRow.toString()	;		  
    }

	public String getRow()
	{
	  StringBuffer  l_sbRow = new StringBuffer();

	  //�P�j�@@���p�҃R�[�h�̃`�F�b�N
	  l_sbRow.append("12345678,");

     //�Q�j�@@���p�Җ��`�F�b�N
     l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");

      //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
      l_sbRow.append("12456432,");
      //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
      l_sbRow.append("afdff1652,");
      //�T�j�@@�����p�X���[�h�̃`�F�b�N
      l_sbRow.append("afdffg652,");
      //�U�j�@@���[���A�h���X�P�̃`�F�b�N
      l_sbRow.append("fgsdgddfsdf453,");
      //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
      l_sbRow.append("sdfsfsdff5453,");
      //�W�j�@@���Ȏ���敪�̃`�F�b�N
      l_sbRow.append("1,");
      //�X�j�@@���p�ґ����̃`�F�b�N     
      l_sbRow.append("1,");
      //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
      l_sbRow.append("1,");
      //�P�P�j�@@�萔���敪�̃`�F�b�N
      l_sbRow.append("a1,");
      //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
      l_sbRow.append("a1,");
      //�P�R�j�@@����\�敪�̃`�F�b�N
      l_sbRow.append("1,");
      //�P�S�j�@@�d�q��t�������̃`�F�b�N
      l_sbRow.append("20080501,");
      //�P�T�j�@@����������m�F���̃`�F�b�N
      l_sbRow.append("20080501,");
      //�P�U�j�@@������ԍ��̃`�F�b�N
      l_sbRow.append("200805011,");
      //�P�V�j�@@���l�̃`�F�b�N
      l_sbRow.append("afjsidfer,");
      //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //�P�X�j�@@����������ʂP�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //20�j�@@���i�R�[�h2�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //21�j�@@�����������2�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //22�j�@@���i�R�[�h3�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //23�j�@@�����������3�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //24�j�@@���i�R�[�h4�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //25�j�@@�����������4�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //26�j�@@���i�R�[�h5�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //27�j�@@�����������5�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //28�j�@@���i�R�[�h6�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //29�j�@@�����������6�̃`�F�b�N 
      l_sbRow.append("12355621,");
      //30�j�@@���i�R�[�h7�̃`�F�b�N   
      l_sbRow.append("fgfff,");
      //31�j�@@�����������7�̃`�F�b�N 
      l_sbRow.append("12355621,");
      if(count == 0 )
      {
          //32�j�@@���i�R�[�h8�̃`�F�b�N   
    	  l_sbRow.append("34535325,");
      }
      else
      {
          //32�j�@@���i�R�[�h8�̃`�F�b�N   
          l_sbRow.append("345345,");
      }
      if(count == 1 )
      {
          //33�j�@@�����������8�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //33�j�@@�����������8�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      if(count == 2)
      {
          //34�j�@@���i�R�[�h9�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //34�j�@@���i�R�[�h9�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 3)
      {
          //35�j�@@�����������9�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //35�j�@@�����������9�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      if(count == 4)
      {
          //36�j�@@���i�R�[�h10�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //36�j�@@���i�R�[�h10�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 5)
      {
          //37�j�@@�����������10�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //37�j�@@�����������10�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      if(count == 6)
      {
          //38�j�@@���i�R�[�h11�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //38�j�@@���i�R�[�h11�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 7)
      {
          //39�j�@@�����������11�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //39�j�@@�����������11�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      if(count == 8)
      {
          //40�j�@@���i�R�[�h12�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //40�j�@@���i�R�[�h12�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 9)
      {
          //41�j�@@�����������12�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //41�j�@@�����������12�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      if(count == 10)
      {
          //42�j�@@���i�R�[�h13�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //42�j�@@���i�R�[�h13�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 11)
      {
          //43�j�@@�����������13�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //43�j�@@�����������13�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 12)
      {
          //44�j�@@���i�R�[�h14�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //44�j�@@���i�R�[�h14�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 13)
      {
          //45�j�@@�����������14�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //45�j�@@�����������14�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 14)
      {
          //46�j�@@���i�R�[�h15�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //46�j�@@���i�R�[�h15�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 15)
      {
          //47�j�@@�����������15�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //47�j�@@�����������15�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 16)
      {
          //48�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //48�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 17)
      {
          //49�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //49�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      if(count == 18)
      {
          //50�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //50�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 19)
      {
          //51�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //51�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 20)
      {
          //52�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //52�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 21)
      {
          //53�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //53�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 22)
      {
          //54�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //54�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 23)
      {
          //55�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //55�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 24)
      {
          //56�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //56�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 25)
      {
          //57�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //57�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 26)
      {
          //58�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //58�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 27)
      {
          //59�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //59�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 28)
      {
          //60�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //60�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 29)
      {
          //61�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //61�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      
      
      if(count == 30)
      {
          //62�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //62�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 31)
      {
          //63�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //63�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 32)
      {
          //64�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //64�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 33)
      {
          //65�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //65�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253,");
      }      
      
      if(count == 34)
      {
          //66�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("34535345,");
      }
      else
      {
          //66�j�@@���i�R�[�h16�̃`�F�b�N   
          l_sbRow.append("435345,");
      }
      if(count == 35)
      {
          //67�j�@@�����������16�̃`�F�b�N 
          l_sbRow.append("3453532535353434");
      }
      else
      {
          //67�j�@@�����������16�̃`�F�b�N 
    	  l_sbRow.append("345353253");
      }
          return l_sbRow.toString()	;		  
    }

    /**
     * validate���׍s(length)<BR>
     */
	public void testValidateDetailsLine_Case012()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        for(int i = 0; i < 36; i++)
        {
            WEB3AdminFXAccOpenUploadCsv l_csv = null;
            count = i;
            String l_strRow = this.getRow();
            try
            {
            	l_csv = new WEB3AdminFXAccOpenUploadCsv();
				l_csv.addRow(l_strRow);
				l_csv.validateDetailsLine(0);
				fail();
			} 
            catch (WEB3BaseException l_ex)
			{
				if(count%2 == 0)
				{
					assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02378,l_ex.getErrorInfo());
				}
				else
				{
					assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02380,l_ex.getErrorInfo());
				}
				
			}
            catch (Exception l_ex)
            {
            	log.error(l_ex.getMessage());
            	fail();
            }
            log.debug("STR_METHOD_NAME");
        }
	}

    /**
     * validate���׍s(digit)<BR>
     */
	public void testValidateDetailsLine_Case013()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        for(int i = 0; i < 18; i++)
        {
            WEB3AdminFXAccOpenUploadCsv l_csv = null;
            count = i;
            String l_strRow = this.getRowDigit();
            try
            {
            	l_csv = new WEB3AdminFXAccOpenUploadCsv();
				l_csv.addRow(l_strRow);
				l_csv.validateDetailsLine(0);
				fail();
			} 
            catch (WEB3BaseException l_ex)
			{
			    assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02379,l_ex.getErrorInfo());
			}
            catch (Exception l_ex)
            {
            	log.error(l_ex.getMessage());
            	fail();
            }
            log.debug("STR_METHOD_NAME");
        }
	}

    /**
     * get���p�ґ���<BR>
     */
	public void testGetMethod_Case001()
	{
        final String STR_METHOD_NAME = "testGetMethod_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();
        StringBuffer l_sbRow = new StringBuffer();

        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        l_sbRow.append("12345678,");
        //�Q�j�@@���p�Җ��`�F�b�N
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //�R�j�@@���O�C���h�c�̃`�F�b�N�@@
        l_sbRow.append("12456432,");
        //�S�j�@@���O�C���p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdff1652,");
        //�T�j�@@�����p�X���[�h�̃`�F�b�N
        l_sbRow.append("afdffg652,");
        //�U�j�@@���[���A�h���X�P�̃`�F�b�N
        l_sbRow.append("fgsdgddfsdf453,");
        //�V�j�@@���[���A�h���X�Q�̃`�F�b�N
        l_sbRow.append("sdfsfsdff5453,");
        //�W�j�@@���Ȏ���敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�X�j�@@���p�ґ����̃`�F�b�N     
        l_sbRow.append("1,"); 
        //�P�O�j�@@���ϕ��@@�̃`�F�b�N     
        l_sbRow.append("1,");
        //�P�P�j�@@�萔���敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�Q�j�@@���X�J�b�g�敪�̃`�F�b�N
        l_sbRow.append("a1,");
        //�P�R�j�@@����\�敪�̃`�F�b�N
        l_sbRow.append("1,");
        //�P�S�j�@@�d�q��t�������̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�T�j�@@����������m�F���̃`�F�b�N
        l_sbRow.append("20080501,");
        //�P�U�j�@@������ԍ��̃`�F�b�N
        l_sbRow.append("2008050112,");
        //�P�V�j�@@���l�̃`�F�b�N
        l_sbRow.append("afjsidfer,");
        //�P�W�j�@@���i�R�[�h�P�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //�P�X�j�@@����������ʂP�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //20�j�@@���i�R�[�h2�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //21�j�@@�����������2�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //22�j�@@���i�R�[�h3�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //23�j�@@�����������3�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //24�j�@@���i�R�[�h4�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //25�j�@@�����������4�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //26�j�@@���i�R�[�h5�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //27�j�@@�����������5�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //28�j�@@���i�R�[�h6�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //29�j�@@�����������6�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //30�j�@@���i�R�[�h7�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //31�j�@@�����������7�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //32�j�@@���i�R�[�h8�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //33�j�@@�����������8�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //34�j�@@���i�R�[�h9�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //35�j�@@�����������9�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //36�j�@@���i�R�[ �h10�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //37�j�@@�����������10�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //38�j�@@���i�R�[�h11�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //39�j�@@�����������11�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //40�j�@@���i�R�[�h12�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //41�j�@@�����������12�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //42�j�@@���i�R�[�h13�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //43�j�@@�����������13�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //44�j�@@���i�R�[�h14�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //45�j�@@�����������14�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //46�j�@@���i�R�[�h15�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //47�j�@@�����������15�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //48�j�@@���i�R�[�h16�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //49�j�@@�����������16�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //50�j�@@���i�R�[ �h17�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //51�j�@@�����������17�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //52�j�@@���i�R�[�h18�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //53�j�@@�����������18�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //54�j�@@���i�R�[�h19�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //55�j�@@�����������19�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //56�j�@@���i�R�[�h20�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //57�j�@@�����������20�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //58�j�@@���i�R�[�h21�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //59�j�@@�����������21�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //60�j�@@���i�R�[�h22�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //61�j�@@�����������22�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //62�j�@@���i�R�[�h23�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //63�j�@@�����������23�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //64�j�@@���i�R�[ �h24�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //65�j�@@�����������24�̃`�F�b�N 
        l_sbRow.append("12355621,");
        //66�j�@@���i�R�[�h25�̃`�F�b�N   
        l_sbRow.append("fgfff,");
        //67�j�@@�����������25�̃`�F�b�N 
        l_sbRow.append("12355621");
        try
        {

			l_csv.addRow(l_sbRow.toString());

			String l_strAvailAttribte = l_csv.getUserAttribute(0);
			String l_strSettlementMethod = l_csv.getSettlementMethod(0);
			String l_strElectronGrantPermissionDay = l_csv.getElectronGrantPermissionDay(0);
			String l_strTradeOperatingManualConfirmationDay = l_csv.getTradeOperatingManualConfirmationDay(0);
			String l_strAgreementBookNumber = l_csv.getAgreementBookNumber(0);
			String l_strProductCode8 = l_csv.getProductCode8(0);
			String l_strProductCode9 = l_csv.getProductCode9(0);
			String l_strProductCode10 = l_csv.getProductCode10(0);
			String l_strProductCode11 = l_csv.getProductCode11(0);
			String l_strProductCode12 = l_csv.getProductCode12(0);
			String l_strProductCode13 = l_csv.getProductCode13(0);
			String l_strProductCode14 = l_csv.getProductCode14(0);
			String l_strProductCode15 = l_csv.getProductCode15(0);
			String l_strProductCode16 = l_csv.getProductCode16(0);
			String l_strProductCode17 = l_csv.getProductCode17(0);
			String l_strProductCode18 = l_csv.getProductCode18(0);
			String l_strProductCode19 = l_csv.getProductCode19(0);
			String l_strProductCode20 = l_csv.getProductCode20(0);
			String l_strProductCode21 = l_csv.getProductCode21(0);
			String l_strProductCode22 = l_csv.getProductCode22(0);
			String l_strProductCode23 = l_csv.getProductCode23(0);
			String l_strProductCode24 = l_csv.getProductCode24(0);
			String l_strProductCode25 = l_csv.getProductCode25(0);

			String l_strOrderQuantityUpper8 = l_csv.getOrderQuantityUpper8(0);
			String l_strOrderQuantityUpper9 = l_csv.getOrderQuantityUpper9(0);
			String l_strOrderQuantityUpper10 = l_csv.getOrderQuantityUpper8(0);
			String l_strOrderQuantityUpper11 = l_csv.getOrderQuantityUpper11(0);
			String l_strOrderQuantityUpper12 = l_csv.getOrderQuantityUpper12(0);
			String l_strOrderQuantityUpper13 = l_csv.getOrderQuantityUpper13(0);
			String l_strOrderQuantityUpper14 = l_csv.getOrderQuantityUpper14(0);
			String l_strOrderQuantityUpper15 = l_csv.getOrderQuantityUpper15(0);
			String l_strOrderQuantityUpper16 = l_csv.getOrderQuantityUpper16(0);
			String l_strOrderQuantityUpper17 = l_csv.getOrderQuantityUpper17(0);
			String l_strOrderQuantityUpper18 = l_csv.getOrderQuantityUpper18(0);
			String l_strOrderQuantityUpper19 = l_csv.getOrderQuantityUpper19(0);
			String l_strOrderQuantityUpper20 = l_csv.getOrderQuantityUpper20(0);
			String l_strOrderQuantityUpper21 = l_csv.getOrderQuantityUpper21(0);
			String l_strOrderQuantityUpper22 = l_csv.getOrderQuantityUpper22(0);
			String l_strOrderQuantityUpper23 = l_csv.getOrderQuantityUpper23(0);
			String l_strOrderQuantityUpper24 = l_csv.getOrderQuantityUpper24(0);
			String l_strOrderQuantityUpper25 = l_csv.getOrderQuantityUpper25(0);

			assertEquals("1",l_strAvailAttribte);
			assertEquals("1",l_strSettlementMethod);
			assertEquals("20080501",l_strElectronGrantPermissionDay);
			assertEquals("20080501",l_strTradeOperatingManualConfirmationDay);
			assertEquals("2008050112",l_strAgreementBookNumber);
			
			assertEquals("fgfff",l_strProductCode8);
			assertEquals("12355621",l_strOrderQuantityUpper8);
			
			assertEquals("fgfff",l_strProductCode9);
			assertEquals("12355621",l_strOrderQuantityUpper9);
			
			assertEquals("fgfff",l_strProductCode10);
			assertEquals("12355621",l_strOrderQuantityUpper10);
			
			assertEquals("fgfff",l_strProductCode11);
			assertEquals("12355621",l_strOrderQuantityUpper11);
			
			assertEquals("fgfff",l_strProductCode12);
			assertEquals("12355621",l_strOrderQuantityUpper12);
			
			assertEquals("fgfff",l_strProductCode13);
			assertEquals("12355621",l_strOrderQuantityUpper13);
			
			assertEquals("fgfff",l_strProductCode14);
			assertEquals("12355621",l_strOrderQuantityUpper14);
			
			assertEquals("fgfff",l_strProductCode15);
			assertEquals("12355621",l_strOrderQuantityUpper15);
			
			assertEquals("fgfff",l_strProductCode16);
			assertEquals("12355621",l_strOrderQuantityUpper16);
			
			assertEquals("fgfff",l_strProductCode17);
			assertEquals("12355621",l_strOrderQuantityUpper17);
			
			assertEquals("fgfff",l_strProductCode18);
			assertEquals("12355621",l_strOrderQuantityUpper18);
			
			assertEquals("fgfff",l_strProductCode19);
			assertEquals("12355621",l_strOrderQuantityUpper19);
			
			assertEquals("fgfff",l_strProductCode20);
			assertEquals("12355621",l_strOrderQuantityUpper20);
			
			assertEquals("fgfff",l_strProductCode21);
			assertEquals("12355621",l_strOrderQuantityUpper21);
			
			assertEquals("fgfff",l_strProductCode22);
			assertEquals("12355621",l_strOrderQuantityUpper22);
			
			assertEquals("fgfff",l_strProductCode23);
			assertEquals("12355621",l_strOrderQuantityUpper23);
			
			assertEquals("fgfff",l_strProductCode24);
			assertEquals("12355621",l_strOrderQuantityUpper24);
			
			assertEquals("fgfff",l_strProductCode25);
			assertEquals("12355621",l_strOrderQuantityUpper25);
		
		} catch (WEB3BaseException l_ex) {
		    log.error(l_ex.getMessage());

			log.exiting("STR_METHOD_NAME");
			fail();
		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			log.exiting("STR_METHOD_NAME");
			fail();
		}
 
	}

 }


@
