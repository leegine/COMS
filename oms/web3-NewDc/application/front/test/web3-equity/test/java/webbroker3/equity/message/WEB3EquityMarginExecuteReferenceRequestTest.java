head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.51.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3EquityMarginExecuteReferenceRequestTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17�@@�֔�(���u) �V�K�쐬
Revesion History : 2007/12/30  ���n(���u) �d�l�ύX���f��1232
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �����E�M�p�������Ɖ�N�G�X�g�N���X�̃e�X�g<BR>
 * @@author �֔�(���u)
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteReferenceRequestTest extends TestBaseForMock
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceRequestTest.class);

    public WEB3EquityMarginExecuteReferenceRequestTest(String arg0)
    {
        super(arg0);
    }
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    WEB3EquityMarginExecuteReferenceRequest l_request = null;
    
    //�P�j�Ɖ�敪�`�F�b�N
    //�P�|�P�j
    //    this.�Ɖ�敪 �� null �̏ꍇ�A�u�Ɖ�敪��null�v�̗�O���X���[����B
    //    class: WEB3BusinessLayerException
    //    tag:   BUSINESS_ERROR_00081
    public void testValidate1()
    {
        final String STR_METHOD_NAME = "testValidate1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = new String();
        l_request.referenceType = null;        

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00081,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�P�|�Q�j
    //this.�Ɖ�敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A�u�Ɖ�敪������`�̒l�v�̗�O���X���[����B
    //�E�h�������Ɖ�h
    //�E�h��������ꗗ�h
    //class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00082
    public void testValidate2()
    {
        final String STR_METHOD_NAME = "testValidate2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = new String();
        l_request.referenceType = "2";        

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00082,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�Q�j���i�敪�`�F�b�N
    //�Q�|�P�j
    //    this.���i�敪 �� null �̏ꍇ�A�u���i�敪��null�v�̗�O���X���[����B
    // �@@ class: WEB3BusinessLayerException
    // �@@ tag:   BUSINESS_ERROR_02182
    public void testValidate3()
    {
        final String STR_METHOD_NAME = "testValidate3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = new String();
        l_request.productDiv = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02182,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�Q�|�Q�j
    //this.���i�敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A�u���i�敪������`�̒l�v�̗�O���X���[����B
    //    �E�h���������A�M�p��� ���ׂāh
    //    �E�h���������h
    //    �E�h�M�p����h
    //     class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_01068
    public void testValidate4()
    {
        final String STR_METHOD_NAME = "testValidate4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = new String();
        l_request.productDiv = "3";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01068,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�R�j����ԋ敪�`�F�b�N
    //this.����ԋ敪 �� null and this.����ԋ敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A
    //�u����ԋ敪������`�̒l�v�̗�O���X���[����B
    //    �E�h�����h
    //    �E�h�ꕔ�����h
    //    �E�h�S�������h
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00626
    public void testValidate5()
    {
        final String STR_METHOD_NAME = "testValidate5()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = new String();
        l_request.execType = "3";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00626,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�R�j����ԋ敪�`�F�b�N
    //this.����ԋ敪 �� null and this.����ԋ敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A
    //�u����ԋ敪������`�̒l�v�̗�O���X���[����B
    //    �E�h�����h
    //    �E�h�ꕔ�����h
    //    �E�h�S�������h
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00626
    public void testValidate6()
    {
        final String STR_METHOD_NAME = "testValidate6()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = new String();
        l_request.execType = "4";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00626,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�R�j����ԋ敪�`�F�b�N
    //this.����ԋ敪 �� null and this.����ԋ敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A
    //�u����ԋ敪������`�̒l�v�̗�O���X���[����B
    //    �E�h�����h
    //    �E�h�ꕔ�����h
    //    �E�h�S�������h
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00626
    public void testValidate7()
    {
        final String STR_METHOD_NAME = "testValidate7()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = new String();
        l_request.execType = "5";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00626,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�j�\�[�g�L�[�`�F�b�N
    //�S�|�P�j
    //    this.�\�[�g�L�[ �� null �̏ꍇ�A�u�\�[�g�L�[��null�v�̗�O���X���[����B
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00231
    public void testValidate8()
    {
        final String STR_METHOD_NAME = "testValidate8()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�|�Q�j
    //this.�\�[�g�L�[.�v�f�� �� 0 �̏ꍇ�A�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00232
    public void testValidate9()
    {
        final String STR_METHOD_NAME = "testValidate9()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[0];

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�|�R�j
    //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
    //�S�|�R�|�P�j
    //�\�[�g�L�[.validate()���R�[������B
    public void testValidate10()
    {
        final String STR_METHOD_NAME = "testValidate10()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "openDate";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //�S�|�R�j
    //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
    //�S�|�R�|�P�j
    //�\�[�g�L�[.validate()���R�[������B
    public void testValidate11()
    {
        final String STR_METHOD_NAME = "testValidate11()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "appraisalProfitLoss";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�|�R�j
    //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
    //�S�|�R�|�P�j
    //�\�[�g�L�[.validate()���R�[������B
    public void testValidate12()
    {
        final String STR_METHOD_NAME = "testValidate12()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "contractType";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�|�R�j
    //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
    //�S�|�R�|�P�j
    //�\�[�g�L�[.validate()���R�[������B
    public void testValidate13()
    {
        final String STR_METHOD_NAME = "testValidate13()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "closeDate";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //�S�|�R�j
    //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
    //�S�|�R�|�P�j
    //�\�[�g�L�[.validate()���R�[������B
    public void testValidate14()
    {
        final String STR_METHOD_NAME = "testValidate14()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "dealingType";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�|�R�j
    //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
    //�S�|�R�|�P�j
    //�\�[�g�L�[.validate()���R�[������B
    public void testValidate15()
    {
        final String STR_METHOD_NAME = "testValidate15()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "orderStartDatetime";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�|�R�j
    //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
    //�S�|�R�|�P�j
    //�\�[�g�L�[.validate()���R�[������B
    public void testValidate16()
    {
        final String STR_METHOD_NAME = "testValidate16()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "orderEndDatetime";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�|�R�j
    //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
    //�S�|�R�|�P�j
    //�\�[�g�L�[.validate()���R�[������B
    public void testValidate17()
    {
        final String STR_METHOD_NAME = "testValidate17()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "estimatedAssetBalanceQuantity";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�|�R�j
    //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
    //�S�|�R�|�P�j
    //�\�[�g�L�[.validate()���R�[������B
    public void testValidate18()
    {
        final String STR_METHOD_NAME = "testValidate18()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "estimatedAppraisalProfitLossBalanceQuantity";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�|�R�j
    //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
    //�S�|�R�|�P�j
    //�\�[�g�L�[.validate()���R�[������B
    public void testValidate19()
    {
        final String STR_METHOD_NAME = "testValidate19()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "contractPrice";
            l_request.sortKeys[i].ascDesc = "D";
        }
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //�T�j�v���y�[�W�ԍ��`�F�b�N
    //
    //�T�|�P�j
    //    this.�v���y�[�W�ԍ� �� null �̏ꍇ�A�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00089
    public void testValidate20()
    {
        final String STR_METHOD_NAME = "testValidate20()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //�T�|�Q�j
    //this.�v���y�[�W�ԍ��������ȊO�̒l�̏ꍇ�A�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00090
    public void testValidate21()
    {
        final String STR_METHOD_NAME = "testValidate21()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "A";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�T�|�R�j
    //this.�v���y�[�W�ԍ� �� 0 �̏ꍇ�A�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00616
    public void testValidate22()
    {
        final String STR_METHOD_NAME = "testValidate22()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "-1";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�U�j�y�[�W���\���s���`�F�b�N
    //�U�|�P�j
    //    this.�y�[�W���\���s�� �� null �̏ꍇ�A�u�y�[�W���\���s����null�v�̗�O���X���[����B
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00091
    public void testValidate23()
    {
        final String STR_METHOD_NAME = "testValidate23()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�U�|�Q�j
    //this.�y�[�W���\���s���������ȊO�̒l�̏ꍇ�A�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00092
    public void testValidate24()
    {
        final String STR_METHOD_NAME = "testValidate24()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "A";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�U�|�R�j
    //this.�y�[�W���\���s�� �� 0 �̏ꍇ�A�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00617
    public void testValidate25()
    {
        final String STR_METHOD_NAME = "testValidate25()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "-1";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�V�j�s��R�[�h�`�F�b�N
    //this.�s��R�[�h �� null and ���L�̒l�ȊO�̏ꍇ�A�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
    //    �E�h�����h
    //    �E�h���h
    //    �E�h���É��h
    //    �E�h�����h
    //    �E�h�D�y�h
    //    �E�hNNM�h
    //    �E�hJASDAQ�h
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00608
    public void testValidate26()
    {
        final String STR_METHOD_NAME = "testValidate26()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�V�j�s��R�[�h�`�F�b�N
    //this.�s��R�[�h �� null and ���L�̒l�ȊO�̏ꍇ�A�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
    //    �E�h�����h
    //    �E�h���h
    //    �E�h���É��h
    //    �E�h�����h
    //    �E�h�D�y�h
    //    �E�hNNM�h
    //    �E�hJASDAQ�h
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00608
    public void testValidate27()
    {
        final String STR_METHOD_NAME = "testValidate27()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "4";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�V�j�s��R�[�h�`�F�b�N
    //this.�s��R�[�h �� null and ���L�̒l�ȊO�̏ꍇ�A�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
    //    �E�h�����h
    //    �E�h���h
    //    �E�h���É��h
    //    �E�h�����h
    //    �E�h�D�y�h
    //    �E�hNNM�h
    //    �E�hJASDAQ�h
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00608
    public void testValidate28()
    {
        final String STR_METHOD_NAME = "testValidate28()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "5";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�V�j�s��R�[�h�`�F�b�N
    //this.�s��R�[�h �� null and ���L�̒l�ȊO�̏ꍇ�A�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
    //    �E�h�����h
    //    �E�h���h
    //    �E�h���É��h
    //    �E�h�����h
    //    �E�h�D�y�h
    //    �E�hNNM�h
    //    �E�hJASDAQ�h
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00608
    public void testValidate29()
    {
        final String STR_METHOD_NAME = "testValidate29()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "N1";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�V�j�s��R�[�h�`�F�b�N
    //this.�s��R�[�h �� null and ���L�̒l�ȊO�̏ꍇ�A�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
    //    �E�h�����h
    //    �E�h���h
    //    �E�h���É��h
    //    �E�h�����h
    //    �E�h�D�y�h
    //    �E�hNNM�h
    //    �E�hJASDAQ�h
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00608
    public void testValidate30()
    {
        final String STR_METHOD_NAME = "testValidate30()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "N2";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�V�j�s��R�[�h�`�F�b�N
    //this.�s��R�[�h �� null and ���L�̒l�ȊO�̏ꍇ�A�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
    //    �E�h�����h
    //    �E�h���h
    //    �E�h���É��h
    //    �E�h�����h
    //    �E�h�D�y�h
    //    �E�hNNM�h
    //    �E�hJASDAQ�h
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00608
    public void testValidate31()
    {
        final String STR_METHOD_NAME = "testValidate31()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "X1";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //�V�j�s��R�[�h�`�F�b�N
    //this.�s��R�[�h �� null and ���L�̒l�ȊO�̏ꍇ�A�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
    //    �E�h�����h
    //    �E�h���h
    //    �E�h���É��h
    //    �E�h�����h
    //    �E�h�D�y�h
    //    �E�hNNM�h
    //    �E�hJASDAQ�h
    //
    // �@@�@@class: WEB3BusinessLayerException
    // �@@�@@tag:   BUSINESS_ERROR_00608
    public void testValidate32()
    {
        final String STR_METHOD_NAME = "testValidate32()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request = new WEB3EquityMarginExecuteReferenceRequest();
        l_request.referenceType = "0";
        l_request.productDiv = "0";
        l_request.execType = "0";
        l_request.sortKeys = new WEB3EquityMarginSortKey[10];
        int l_intSortKeyLength = l_request.sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            l_request.sortKeys[i] = new WEB3EquityMarginSortKey();
            l_request.sortKeys[i].keyItem = "productCode";
            l_request.sortKeys[i].ascDesc = "D";
        }
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "99";
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���������敪�̒l�����݂��Ȃ��R�[�h�l�ł�
     * 
     * �X���[:BUSINESS_ERROR_00212�̃��b�Z�[�W
     *
     */
    public void testValidate_C0033()
    {
        final String STR_METHOD_NAME = "testValidate_C0033()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.l_request = new WEB3EquityMarginExecuteReferenceRequest();
            
            
            WEB3EquityMarginSortKey[] sortKeys = new WEB3EquityMarginSortKey[1];
            sortKeys[0] = new WEB3EquityMarginSortKey();
            sortKeys[0].keyItem = "productCode";
            sortKeys[0].ascDesc = "A";
            this.l_request.sortKeys = sortKeys;
            // referenceType
            this.l_request.referenceType = "0";
            
            // productDiv
            this.l_request.productDiv = "1";
            //execType
            this.l_request.execType = "0";
            // pageIndex
            this.l_request.pageIndex = "1";
            this.l_request.pageSize ="1";
            this.l_request.marketCode = "1";
            
            // ���������敪
            this.l_request.orderCondType = "3";
            this.l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00212,l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ���������敪�̒l�����݂��Ȃ��R�[�h�l�ł�
     * 
     * �X���[:BUSINESS_ERROR_00212�̃��b�Z�[�W
     *
     */
    public void testValidate_C0034()
    {
        final String STR_METHOD_NAME = "testValidate_C0034()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.l_request = new WEB3EquityMarginExecuteReferenceRequest();
            
            
            WEB3EquityMarginSortKey[] sortKeys = new WEB3EquityMarginSortKey[1];
            sortKeys[0] = new WEB3EquityMarginSortKey();
            sortKeys[0].keyItem = "productCode";
            sortKeys[0].ascDesc = "A";
            this.l_request.sortKeys = sortKeys;
            // referenceType
            this.l_request.referenceType = "0";
            
            // productDiv
            this.l_request.productDiv = "1";
            //execType
            this.l_request.execType = "0";
            // pageIndex
            this.l_request.pageIndex = "1";
            this.l_request.pageSize ="1";
            this.l_request.marketCode = "1";
            
            // ���������敪
            this.l_request.orderCondType = "0";
            this.l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ����d�l�ύX1232
     * 
     * ����ʉ�
     */
    public void testValidate_C0035()
    {
        final String STR_METHOD_NAME = "testValidate_C0035()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = "0";
            this.l_request.productDiv = "1";
            WEB3EquityMarginSortKey[] sortKeys = new WEB3EquityMarginSortKey[1];
            sortKeys[0] = new WEB3EquityMarginSortKey();
            sortKeys[0].keyItem = "productCode";
            sortKeys[0].ascDesc = "A";
            this.l_request.sortKeys = sortKeys;
            this.l_request.pageIndex = "1";
            this.l_request.pageSize ="1";
            this.l_request.marketCode = "11";
            this.l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ����d�l�ύX1232
     * 
     * �e�o�ُ�M���FBUSINESS_ERROR_00608
     */
    public void testValidate_C0036()
    {
        final String STR_METHOD_NAME = "testValidate_C0036()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.l_request = new WEB3EquityMarginExecuteReferenceRequest();
            l_request.referenceType = "0";
            this.l_request.productDiv = "1";
            WEB3EquityMarginSortKey[] sortKeys = new WEB3EquityMarginSortKey[1];
            sortKeys[0] = new WEB3EquityMarginSortKey();
            sortKeys[0].keyItem = "productCode";
            sortKeys[0].ascDesc = "A";
            this.l_request.sortKeys = sortKeys;
            this.l_request.pageIndex = "1";
            this.l_request.pageSize ="1";
            this.l_request.marketCode = "";
            this.l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
}
@
