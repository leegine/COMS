head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSLAccountOpenApplyListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҏ،��S�ۃ��[���\���ڋq�ꗗ���N�G�X�g�̃e�X�g�N���X(WEB3AdminSLAccountOpenApplyListRequestTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 �����q (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author �����q
 *
 */
public class WEB3AdminSLAccountOpenApplyListRequestTest extends TestBaseForMock
{

        private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminSLAccountOpenApplyListRequestTest.class);
        
        WEB3AdminSLAccountOpenApplyListRequest l_request =
            new WEB3AdminSLAccountOpenApplyListRequest();
        public WEB3AdminSLAccountOpenApplyListRequestTest(String arg0)
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

        public void testValidate_case0001()
        {
            final String STR_METHOD_NAME = "testValidate_case0001()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "sadf";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("���X�R�[�h�̃T�C�Y���s���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0002()
        {
            final String STR_METHOD_NAME = "testValidate_case0002()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "1234";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("���X�R�[�h�̃T�C�Y���s���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0003()
        {
            final String STR_METHOD_NAME = "testValidate_case0003()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "�P�Q�R";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("���X�R�[�h�����l�ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0004()
        {
            final String STR_METHOD_NAME = "testValidate_case0004()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "abc";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("���X�R�[�h�����l�ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0005()
        {
            final String STR_METHOD_NAME = "testValidate_case0005()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "1.2";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("���X�R�[�h�����l�ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0006()
        {
            final String STR_METHOD_NAME = "testValidate_case0006()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "asdffgd";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�ڋq�R�[�h�̃T�C�Y���s���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0007()
        {
            final String STR_METHOD_NAME = "testValidate_case0007()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "1234567";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�ڋq�R�[�h�̃T�C�Y���s���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0008()
        {
            final String STR_METHOD_NAME = "testValidate_case0008()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "�P�Q�R�S�T�U";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0009()
        {
            final String STR_METHOD_NAME = "testValidate_case0009()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "asdasd";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0010()
        {
            final String STR_METHOD_NAME = "testValidate_case0010()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "1.3212";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0011()
        {
            final String STR_METHOD_NAME = "testValidate_case0011()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "asdfghjklbcvb";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�X�g�b�N���[�������ԍ��̃T�C�Y���s���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0012()
        {
            final String STR_METHOD_NAME = "testValidate_case0012()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "012345678912";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�X�g�b�N���[�������ԍ��̃T�C�Y���s���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0013()
        {
            final String STR_METHOD_NAME = "testValidate_case0013()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "�O�P�Q�R�S�T�U�V�W�X";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�X�g�b�N���[�������ԍ������p�����ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0014()
        {
            final String STR_METHOD_NAME = "testValidate_case0014()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "asdfghjklb";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�X�g�b�N���[�������ԍ������p�����ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0015()
        {
            final String STR_METHOD_NAME = "testValidate_case0015()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "1.01234567";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�X�g�b�N���[�������ԍ������p�����ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0016()
        {
            final String STR_METHOD_NAME = "testValidate_case0016()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "6";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�\���󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0017()
        {
            final String STR_METHOD_NAME = "testValidate_case0017()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�\�����i���j�͐\�����i���j���傫���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0018()
        {
            final String STR_METHOD_NAME = "testValidate_case0018()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�����J�ݓ��i���j�͌����J�ݓ��i���j���傫���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0019()
        {
            final String STR_METHOD_NAME = "testValidate_case0019()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708008","yyyyMMdd");
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�����From�͐����To���傫���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0020()
        {
            final String STR_METHOD_NAME = "testValidate_case0020()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708008","yyyyMMdd");
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("����From�͉���To���傫���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0021()
        {
            final String STR_METHOD_NAME = "testValidate_case0021()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708008","yyyyMMdd");
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("����From�͕���To���傫���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0022()
        {
            final String STR_METHOD_NAME = "testValidate_case0022()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�\�[�g�L�[�����w��ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0023()
        {
            final String STR_METHOD_NAME = "testValidate_case0023()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[]{};
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�\�[�g�L�[�̗v�f�����O�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0024()
        {
            final String STR_METHOD_NAME = "testValidate_case0024()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                l_request.sortKeys[0] = sortkey;
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�\�[�g�L�[�̃L�[���ڂ����w��ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0025()
        {
            final String STR_METHOD_NAME = "testValidate_case0025()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                l_request.sortKeys[0] = sortkey;
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�����^�~�������w��ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0026()
        {
            final String STR_METHOD_NAME = "testValidate_case0026()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "a";
                l_request.sortKeys[0] = sortkey;
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0027()
        {
            final String STR_METHOD_NAME = "testValidate_case0027()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "d";
                l_request.sortKeys[0] = sortkey;
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0028()
        {
            final String STR_METHOD_NAME = "testValidate_case0028()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "b";
                l_request.sortKeys[0] = sortkey;
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0029()
        {
            final String STR_METHOD_NAME = "testValidate_case0029()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�v���y�[�W�ԍ������w��ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0030()
        {
            final String STR_METHOD_NAME = "testValidate_case0030()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.pageIndex = "1.23";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�v���y�[�W�ԍ��������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0031()
        {
            final String STR_METHOD_NAME = "testValidate_case0031()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.pageIndex = "-123";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�v���y�[�W�ԍ��������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0032()
        {
            final String STR_METHOD_NAME = "testValidate_case0032()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.pageIndex = "abc";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�v���y�[�W�ԍ��������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0033()
        {
            final String STR_METHOD_NAME = "testValidate_case0033()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.pageIndex = "�P�Q�R";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�v���y�[�W�ԍ��������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0034()
        {
            final String STR_METHOD_NAME = "testValidate_case0034()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.pageIndex = "123";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�y�[�W���\���s���̓��͂��s���ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0035()
        {
            final String STR_METHOD_NAME = "testValidate_case0035()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.pageIndex = "123";
                l_request.pageSize = "1.23";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�y�[�W���\���s���������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0036()
        {
            final String STR_METHOD_NAME = "testValidate_case0036()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.pageIndex = "123";
                l_request.pageSize = "-12";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�y�[�W���\���s���������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0037()
        {
            final String STR_METHOD_NAME = "testValidate_case0037()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.pageIndex = "123";
                l_request.pageSize = "acv";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�y�[�W���\���s���������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0038()
        {
            final String STR_METHOD_NAME = "testValidate_case0038()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.pageIndex = "123";
                l_request.pageSize = "�P�Q�R";
                l_request.validate();
                fail();
                
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                assertEquals("�y�[�W���\���s���������ȊO�̒l�ł��B", l_ex.getErrorMessage());
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        
        public void testValidate_case0039()
        {
            final String STR_METHOD_NAME = "testValidate_case0039()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                l_request.branchCode = "123";
                l_request.accountCode = "123456";
                l_request.stockLoanAccount = "101234567";
                l_request.applyStatus = "0";
                l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.applyDateTo = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
                l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708010","yyyyMMdd");
                l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.executeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.cancelDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
                l_request.closeDateTo = WEB3DateUtility.getDate("200708009","yyyyMMdd");
                l_request.sortKeys = new WEB3SLSortKey[1];
                WEB3SLSortKey sortkey = new WEB3SLSortKey();
                sortkey.keyItem = "a";
                sortkey.ascDesc = "D";
                l_request.sortKeys[0] = sortkey;
                l_request.pageIndex = "123";
                l_request.pageSize = "123";
                l_request.validate();
                
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
            
            log.exiting(STR_METHOD_NAME);
        }
}
@
