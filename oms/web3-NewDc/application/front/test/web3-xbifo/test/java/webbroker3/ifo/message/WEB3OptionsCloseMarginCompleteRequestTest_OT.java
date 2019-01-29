head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginCompleteRequestTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍϊ������N�G�X�g�e�X�g�N���X(WEB3OptionsCloseMarginCompleteRequestTestzj.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/06/12 ���� �V�K�쐬                          
*/

package webbroker3.ifo.message;

import java.util.Date;

import test.util.JunitTestBase;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCloseMarginCompleteRequestTest_OT extends JunitTestBase
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginCompleteRequestTest_OT.class);
    
    WEB3OptionsCloseMarginCompleteRequest l_request = null;

    public WEB3OptionsCloseMarginCompleteRequestTest_OT(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3OptionsCloseMarginCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��
     */
    public void test_validate_case1()
    {
        final String STR_METHOD_NAME = "test_validate_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�ԍό���=null �̏ꍇ
     */
    public void test_validate_case2()
    {
        final String STR_METHOD_NAME = "test_validate_case2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�ԍό��ʂ̗v�f��=0 �̏ꍇ
     */
    public void test_validate_case3()
    {
        final String STR_METHOD_NAME = "test_validate_case3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ�����null and this.���Ϗ������i�ȉ��̒l�j �̏ꍇ
�@@�@@ *�@@�E�h0�F�����_���h 
�@@�@@ *�@@�E�h1�F�P���v���h 
�@@�@@ *�@@�E�h2�F�P�������h 
�@@�@@ *�@@�E�h3�F�������h 
     */
    public void test_validate_case4()
    {
        final String STR_METHOD_NAME = "test_validate_case4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "5";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00179);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�ԍό��ʂ̗v�f��>1and this.���Ϗ���==null�̏ꍇ
     */
    public void test_validate_case5()
    {
        final String STR_METHOD_NAME = "test_validate_case5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] =
                {l_closeMarginContractUnit1, l_closeMarginContractUnit2};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02304);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=null and this.��������=null �̏ꍇ
     */
    public void test_validate_case6()
    {
        final String STR_METHOD_NAME = "test_validate_case6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=1�F�P���v��and this.��������=null �̏ꍇ
     */
    public void test_validate_case7()
    {
        final String STR_METHOD_NAME = "test_validate_case7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=2�F�P���v��and this.��������=null �̏ꍇ
     */
    public void test_validate_case8()
    {
        final String STR_METHOD_NAME = "test_validate_case8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=3�F�P���v��and this.��������=null �̏ꍇ
     */
    public void test_validate_case9()
    {
        final String STR_METHOD_NAME = "test_validate_case9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�������ʁ�null and this.�������ʁ����� �̏ꍇ
     */
    public void test_validate_case10()
    {
        final String STR_METHOD_NAME = "test_validate_case10()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "a";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00075);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�������ʁ�null and this.��������<0 �̏ꍇ
     */
    public void test_validate_case11()
    {
        final String STR_METHOD_NAME = "test_validate_case11()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "-1";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�������ʁ�null and this.��������=0 �̏ꍇ
     */
    public void test_validate_case12()
    {
        final String STR_METHOD_NAME = "test_validate_case12()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "0";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=�h0�F�����_���h �̏ꍇ�A�ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B 
     *   �ԍό��ʂ�validate()���\�b�h���Ăяo���B 
     */
    public void test_validate_case13()
    {
        final String STR_METHOD_NAME = "test_validate_case13()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=�h0�F�����_���h �̏ꍇ�ԍό���.���Ϗ���=null  
     */
    public void test_validate_case14()
    {
        final String STR_METHOD_NAME = "test_validate_case14()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00180);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=�h0�F�����_���h �̏ꍇ�ԍό���.���Ϗ���= 0 
     */
    public void test_validate_case15()
    {
        final String STR_METHOD_NAME = "test_validate_case15()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "001";
            l_closeMarginContractUnit.settlePriority = "0";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00180);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=�h0�F�����_���h �̏ꍇ�ԍό��ʂ̑S�v�f�ɂ��āA
     * �ԍό���.���Ϗ���>0 and �ԍό���.����>0 �ƂȂ� 
     * �g�ݍ��킹�����݂��Ȃ��ꍇ
     */
    public void test_validate_case16()
    {
        final String STR_METHOD_NAME = "test_validate_case16()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "001";
            l_closeMarginContractUnit.settlePriority = "1";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00180);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=null�̏ꍇ,�ԍό���.���Ϗ��ʁ�null 
     */
    public void test_validate_case17()
    {
        final String STR_METHOD_NAME = "test_validate_case17()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "001";
            l_closeMarginContractUnit.settlePriority = "1";
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = null;
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00183);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=null�̏ꍇ,�ԍό��ʂ�validate()���\�b�h���Ăяo��
     */
    public void test_validate_case18()
    {
        final String STR_METHOD_NAME = "test_validate_case18()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = null;
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=1�F�P���v���̏ꍇ,�ԍό���.���Ϗ��ʁ�null 
     */
    public void test_validate_case19()
    {
        final String STR_METHOD_NAME = "test_validate_case19()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = "1";
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00183);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=1�F�P���v���̏ꍇ,�ԍό��ʂ�validate()���\�b�h���Ăяo��
     */
    public void test_validate_case20()
    {
        final String STR_METHOD_NAME = "test_validate_case20()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=2�F�P�������̏ꍇ,�ԍό���.���Ϗ��ʁ�null 
     */
    public void test_validate_case21()
    {
        final String STR_METHOD_NAME = "test_validate_case21()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = "1";
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "2";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00183);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=2�F�P�������̏ꍇ,�ԍό��ʂ�validate()���\�b�h���Ăяo��
     */
    public void test_validate_case22()
    {
        final String STR_METHOD_NAME = "test_validate_case22()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "2";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=3�F�������̏ꍇ,�ԍό���.���Ϗ��ʁ�null 
     */
    public void test_validate_case23()
    {
        final String STR_METHOD_NAME = "test_validate_case23()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = "1";
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00183);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=3�F�������̏ꍇ,�ԍό��ʂ�validate()���\�b�h���Ăяo��
     */
    public void test_validate_case24()
    {
        final String STR_METHOD_NAME = "test_validate_case24()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.����ID��null�̏ꍇ,this.�m�F���P���������l�i�����́j�̒l�ł����
     */
    public void test_validate_case25()
    {
        final String STR_METHOD_NAME = "test_validate_case25()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "10";
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "2";
            l_request.orderId = "0001";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00206);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.����ID��null�̏ꍇ,this.�m�F���������������l�i�����́j�̒l�ł����
     */
    public void test_validate_case26()
    {
        final String STR_METHOD_NAME = "test_validate_case26()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "10";
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "2";
            l_request.orderId = "0001";
            l_request.checkPrice = "100";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00078);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * �ȏ�check����ʉ�
     */
    public void test_validate_case27()
    {
        final String STR_METHOD_NAME = "test_validate_case27()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "10";
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "2";
            l_request.orderId = "0001";
            l_request.checkPrice = "100";
            l_request.checkDate = new Date("2004/07/16");
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��
     */
    public void test_validateATReserveOrder_case1()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.����ID��null�̏ꍇ
     */
    public void test_validateATReserveOrder_case2()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00600);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�ԍό���=null �̏ꍇ
     */
    public void test_validateATReserveOrder_case3()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�ԍό��ʂ̗v�f��=0 
     */
    public void test_validateATReserveOrder_case4()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ�����null and 
     * this.���Ϗ������i�ȉ��̒l�j �̏ꍇ
�@@�@@ *�@@�E�h0�F�����_���h 
�@@�@@ *�@@�E�h1�F�P���v���h 
�@@�@@ *�@@�E�h2�F�P�������h 
�@@�@@ *�@@�E�h3�F�������h 
     */
    public void test_validateATReserveOrder_case5()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "5";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00179);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=null  and this.��������=null �̏ꍇ
     */
    public void test_validateATReserveOrder_case6()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = null;
            l_request.opOrderQuantity = null;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=1�F�P���v��and this.��������=null �̏ꍇ
     */
    public void test_validateATReserveOrder_case7()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = null;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=2�F�P���v��and this.��������=null �̏ꍇ
     */
    public void test_validateATReserveOrder_case8()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "2";
            l_request.opOrderQuantity = null;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.���Ϗ���=3�F�P���v��and this.��������=null �̏ꍇ
     */
    public void test_validateATReserveOrder_case9()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = null;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�������ʁ�null and this.�������ʁ����� �̏ꍇ
     */
    public void test_validateATReserveOrder_case10()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case10()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "a";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00075);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�������ʁ�null and this.��������<0 �̏ꍇ
     */
    public void test_validateATReserveOrder_case11()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case11()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "0";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�������ʁ�null and this.��������=0 �̏ꍇ
     */
    public void test_validateATReserveOrder_case12()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case12()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "-1";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * ���Ϗ������h0�F�����_���h�̏ꍇ�ԍό���.�������� ��null 
     */
    public void test_validateATReserveOrder_case13()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case13()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03060);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * ���Ϗ������h0�F�����_���h�̏ꍇ�ԍό���.�������� �������ȊO 
     */
    public void test_validateATReserveOrder_case14()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case14()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "a";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03060);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * ���Ϗ������h0�F�����_���h�̏ꍇ�ԍό���.�������� ���O�ȉ��̐���
     */
    public void test_validateATReserveOrder_case15()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case15()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "-2";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03060);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * ���Ϗ������h0�F�����_���h�̏ꍇ�ԍό���.�������� ���E�W���𒴂��鐔��
     */
    public void test_validateATReserveOrder_case16()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case16()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "100000000";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03060);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�m�F���P����null�ł������ꍇ
     */
    public void test_validateATReserveOrder_case17()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case17()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00206);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.�m�F����������null�ł������ꍇ
     */
    public void test_validateATReserveOrder_case18()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case18()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.checkPrice = "100";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00078);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * �ȏ�check����ʉ�
     */
    public void test_validateATReserveOrder_case19()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case19()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.checkPrice = "100";
            l_request.checkDate = new Date("2004/07/16");
            l_request.validateATReserveOrder();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    private void commonRequestPass()
    {
        l_request.orderPriceDiv = "1";
        l_request.limitPrice = "5";
        l_request.execCondType = "1";
        l_request.expirationDateType = "2";
        l_request.expirationDate = WEB3DateUtility.getDate("20080606", "yyyyMMdd");
        l_request.orderCondType = "1";
        l_request.stopPremium_underlyingAssets = "0";
        l_request.stopOrderCondPrice = "5";
        l_request.stopOrderCondOperator = "1";
    }  
}
@
