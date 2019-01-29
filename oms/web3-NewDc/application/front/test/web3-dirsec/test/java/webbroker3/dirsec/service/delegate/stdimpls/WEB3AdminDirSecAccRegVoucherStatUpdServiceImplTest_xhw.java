head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/18 ���G�� (���u) �V�K�쐬
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.AccOpenVoucherStatusDao;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccVoucherRecordDetail;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw.class);

    public WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl l_impl =
        new WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl();
    public WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw(String arg0)
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

    /*
     * "�R-�P�j �̖߂�l�i�����J�݌����q�e�[�u��List�j����=1 �̏ꍇ
     * �ԉ�?���ʃR�[�h�I�m�F"
     * "�i�����j�،���ЃR�[�h=123
     * �i�����jl_request.���X�R�[�h=624
     * �i�����jl_request.�ڋq�R�[�h=123456
     * ��搴������J�݌����q�e�[�u��
     * �V��������J�݌����q�e�[�u���������꞊�ɝ��@@���F
     * 1�j�،���ЃR�[�h=123�C���X�R�[�h=624�C�ڋq�R�[�h=123456�C���ʃR�[�h=444"
     * assertEquals("444", ���@@�ԉ�?[0]);
     * ����
     */
    public void testGetRequestCode_case001()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            String l_strInstitutionCode = "123";

            ExpAccountOpenParams l_expAccountOpenParams = this.getExpAccountOpenParams();
            l_expAccountOpenParams.setInstitutionCode("123");
            l_expAccountOpenParams.setBranchCode("624");
            l_expAccountOpenParams.setAccountCode("123456");
            l_expAccountOpenParams.setAccOpenRequestNumber("444");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getRequestNumber", new Class[]{
                    WEB3AdminDirSecAccRegVoucherSearchResRequest.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{l_request, l_strInstitutionCode});
            Object[] l_objA = (Object[])l_obj;
            log.debug("l_objA[0]" + l_objA[0]);
            assertEquals("444", l_objA[0]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�R-�P�j �̖߂�l�i�����J�݌����q�e�[�u��List�j����=0 �̏ꍇ
     * �ԉ�?���ʃR�[�h�I�m�F"
     * "�i�����j�،���ЃR�[�h=123
     * �i�����jl_request.���X�R�[�h=624
     * �i�����jl_request.�ڋq�R�[�h=123456
     * ��������J�݌����q�e�[�u��"  assertEquals(null, ���@@�ԉ�?);
     */
    public void testGetRequestCode_case002()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            String l_strInstitutionCode = "123";

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getRequestNumber", new Class[]{
                    WEB3AdminDirSecAccRegVoucherSearchResRequest.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{l_request, l_strInstitutionCode});
            assertEquals(null, l_obj);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�R-�P�j �̖߂�l�i�����J�݌����q�e�[�u��List�j����=3 �̏ꍇ
     * �ԉ�?���ʃR�[�h�I�m�F"    "�i�����j�،���ЃR�[�h=123
     * �i�����jl_request.���X�R�[�h=624
     * �i�����jl_request.�ڋq�R�[�h=123456
     * ��搴������J�݌����q�e�[�u��
     * �V��������J�݌����q�e�[�u��������3���ɝ��@@���F
     * 1�j�،���ЃR�[�h=123�C���X�R�[�h=624�C�ڋq�R�[�h=123456�C���ʃR�[�h=444
     * 2�j�،���ЃR�[�h=123�C���X�R�[�h=624�C�ڋq�R�[�h=123456�C���ʃR�[�h=555
     * 3�j�،���ЃR�[�h=123�C���X�R�[�h=624�C�ڋq�R�[�h=123456�C���ʃR�[�h=666"
     * "assertEquals(""444"", ���@@�ԉ�?[0]);
     * assertEquals(""555"", ���@@�ԉ�?[1]);
     * assertEquals(""666"", ���@@�ԉ�?[2]);"
     */
    public void testGetRequestCode_case003()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            String l_strInstitutionCode = "123";

            ExpAccountOpenParams l_expAccountOpenParams = this.getExpAccountOpenParams();
            l_expAccountOpenParams.setInstitutionCode("123");
            l_expAccountOpenParams.setBranchCode("624");
            l_expAccountOpenParams.setAccountCode("123456");
            l_expAccountOpenParams.setAccOpenRequestNumber("444");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            l_expAccountOpenParams.setInstitutionCode("123");
            l_expAccountOpenParams.setBranchCode("624");
            l_expAccountOpenParams.setAccountCode("123456");
            l_expAccountOpenParams.setAccOpenRequestNumber("555");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            l_expAccountOpenParams.setInstitutionCode("123");
            l_expAccountOpenParams.setBranchCode("624");
            l_expAccountOpenParams.setAccountCode("123456");
            l_expAccountOpenParams.setAccOpenRequestNumber("666");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getRequestNumber", new Class[]{
                    WEB3AdminDirSecAccRegVoucherSearchResRequest.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{l_request, l_strInstitutionCode});
            Object[] l_objA = (Object[])l_obj;
            log.debug("l_objA[0]" + l_objA[0]);
            assertEquals("444", l_objA[0]);
            assertEquals("555", l_objA[1]);
            assertEquals("666", l_objA[2]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�i�����j�`�[�쐬�󋵕ϊ��t���O == TRUE �̏ꍇ&&�i�����jstatus == 3�i��t�����j �̎�
     * 4 �i��M�ρj��ԋp����B
     * �i�����j�`�[�쐬�󋵕ϊ��t���O == TRUE
     * �i�����jstatus == 3"
     * assertEquals("4", ���@@�ԉ�?);
     */
    public void testChangeStatus_case001()
    {
        final String STR_METHOD_NAME = "testChangeStatus_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strStatus = "3";
            boolean l_blnIsVoucherStatusChangeFlag = true;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "changeStatus", new Class[]{
                    String.class, boolean.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strStatus, new Boolean(l_blnIsVoucherStatusChangeFlag)});
            String l_strResult = (String)l_obj;
            assertEquals("4", l_strResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�i�����j�`�[�쐬�󋵕ϊ��t���O == TRUE �̏ꍇ&&�i�����jstatus == 4�i��t�G���[�j �̎�
     * 6�i��M�G���[�j��ԋp����B
     * �i�����j�`�[�쐬�󋵕ϊ��t���O == TRUE
     * �i�����jstatus == 4"
     *  assertEquals("6", ���@@�ԉ�?);
     */
    public void testChangeStatus_case002()
    {
        final String STR_METHOD_NAME = "testChangeStatus_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strStatus = "4";
            boolean l_blnIsVoucherStatusChangeFlag = true;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "changeStatus", new Class[]{
                    String.class, boolean.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strStatus, new Boolean(l_blnIsVoucherStatusChangeFlag)});
            String l_strResult = (String)l_obj;
            assertEquals("6", l_strResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�i�����j�`�[�쐬�󋵕ϊ��t���O == FALSE �̏ꍇ
     * �i�����jstatus��ԋp����B
     * �i�����j�`�[�쐬�󋵕ϊ��t���O == false
     * �i�����jstatus == 4"    assertEquals("4", ���@@�ԉ�?);
     */
    public void testChangeStatus_case003()
    {
        final String STR_METHOD_NAME = "testChangeStatus_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strStatus = "4";
            boolean l_blnIsVoucherStatusChangeFlag = false;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "changeStatus", new Class[]{
                    String.class, boolean.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strStatus, new Boolean(l_blnIsVoucherStatusChangeFlag)});
            String l_strResult = (String)l_obj;
            assertEquals("4", l_strResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �p�����[�^�l��NULL�I
     * �i�����j���R�[�hList==null
     * throw 80017
     */
    public void testCreateAccInfoRegVoucher_case001()
    {
        final String STR_METHOD_NAME = "testCreateAccInfoRegVoucher_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            List l_lisRecordList = null;
            boolean l_blnIsAccOpenVoucherFlag = false;
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            ArrayList l_lisRecords = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "createAccInfoRegVoucher", new Class[]{
                        List.class, boolean.class, String.class, String.class, ArrayList.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_lisRecordList, new Boolean(l_blnIsAccOpenVoucherFlag),
                l_strBranchCode, l_strAccountCode, l_lisRecords});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�i�����j���R�[�hList�̒� == 1
     * �i�����j�����J�ݓ`�[�t���O == FALSE �̏ꍇ
     * �i�����j���R�[�hList[0].�،���ЃR�[�h = 123
     * �i�����j���R�[�hList[0].���X�R�[�h = 624
     * �i�����j���R�[�hList[0].�ڋq�R�[�h = 123456
     * �i�����j���R�[�hList[0].�f�[�^�R�[�h = 222
     * �i�����j���R�[�hList[0].���ʃR�[�h = 333
     * �i�����j���R�[�hList[0].�`�[�쐬�� = 1
     * �i�����j���R�[�hList[0].�G���[���R�R�[�h =11
     * �i�����j���R�[�hList[0].�`�[���M���� = �g20070606�h
     * �i�����j���R�[�hList[0].�`�[��M���� =�g20070606�h
     * �i�����j���R�[�hList[index].�A�����  = 1
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h = 123
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h = 624
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h = 123456
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h = 222
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h = 333
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� = 1
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h = 11
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = �g20070606�h
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = �g20070606�h
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O = true
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� = 1
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = null"
     */
    public void testCreateAccInfoRegVoucher_case002()
    {
        final String STR_METHOD_NAME = "testCreateAccInfoRegVoucher_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            VariousInformParams l_variousInformParams = new VariousInformParams();
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setAccountCode("123456");
            l_variousInformParams.setBranchCode("624");
            l_variousInformParams.setRequestCode("222");
            l_variousInformParams.setRequestNumber("333");
            l_variousInformParams.setStatus("1");
            l_variousInformParams.setErrorReasonCode("11");
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setSendTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            l_variousInformParams.setReceiptTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            List l_lisRecordList = new ArrayList();
            l_lisRecordList.add(l_variousInformParams);
            boolean l_blnIsAccOpenVoucherFlag = false;
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            ArrayList l_lisRecords = new ArrayList();

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "createAccInfoRegVoucher", new Class[]{
                        List.class, boolean.class, String.class, String.class, ArrayList.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_lisRecordList, new Boolean(l_blnIsAccOpenVoucherFlag),
                l_strBranchCode, l_strAccountCode, l_lisRecords});

//            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn = (WEB3AdminDirSecAccVoucherRecordDetail)l_obj;
            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn =
                (WEB3AdminDirSecAccVoucherRecordDetail)l_lisRecords.get(0);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h = 123
            assertEquals("123", l_paramsReturn.institutionCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h = 624
            assertEquals("624", l_paramsReturn.branchCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h = 123456
            assertEquals("123456", l_paramsReturn.accountCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h = 222
            assertEquals("222", l_paramsReturn.dataCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h = 333
            assertEquals("333", l_paramsReturn.requestNumber);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� = 1
            assertEquals("1", l_paramsReturn.voucherMakeStatus);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h = 11
            assertEquals("11", l_paramsReturn.errorReasonCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = �g20070606�h
            log.debug("�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = " + l_paramsReturn.voucherSendTimestamp);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = �g20070606�h
            log.debug("�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = " + l_paramsReturn.voucherRecvTimestamp);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O = true
            assertEquals(false, l_paramsReturn.voucherFlag);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� = 1
            assertEquals("1", l_paramsReturn.infoType);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = null"
            assertEquals(null, l_paramsReturn.voucherNumber);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�i�����j���R�[�hList�̒� == 1
     * �i�����j�����J�ݓ`�[�t���O == true �̏ꍇ
     * �i�����j���R�[�hList[0].�،���ЃR�[�h = 123
     * �i�����j���R�[�hList[0].���X�R�[�h = 624
     * �i�����j���R�[�hList[0].�ڋq�R�[�h = 123456
     * �i�����j���R�[�hList[0].�f�[�^�R�[�h = 222
     * �i�����j���R�[�hList[0].���ʃR�[�h = 333
     * �i�����j���R�[�hList[0].�`�[�쐬�� = 1
     * �i�����j���R�[�hList[0].�G���[���R�R�[�h =11
     * �i�����j���R�[�hList[0].�`�[���M���� = �g20070606�h
     * �i�����j���R�[�hList[0].�`�[��M���� =�g20070606�h
     * �i�����j���R�[�hList[index].�`�[�ʔ� = 1
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h = 123
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h = 624
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h = 123456
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h = 222
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h = 333
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� = 1
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h = 11
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = �g20070606�h
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = �g20070606�h
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O = true
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� = null
     * �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = 1"   ����
     */
    public void testCreateAccInfoRegVoucher_case003()
    {
        final String STR_METHOD_NAME = "testCreateAccInfoRegVoucher_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            AccOpenVoucherStatusParams l_vccOpenVoucherStatusParams = new AccOpenVoucherStatusParams();
            l_vccOpenVoucherStatusParams.setInstitutionCode("123");
            l_vccOpenVoucherStatusParams.setAccOpenRequestNumber("333");
            l_vccOpenVoucherStatusParams.setRequestCode("222");
            l_vccOpenVoucherStatusParams.setVoucherStatus("1");
            l_vccOpenVoucherStatusParams.setErrorCode("11");
            l_vccOpenVoucherStatusParams.setSerialNo("1");
            l_vccOpenVoucherStatusParams.setSendTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            l_vccOpenVoucherStatusParams.setRecvTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            List l_lisRecordList = new ArrayList();
            l_lisRecordList.add(l_vccOpenVoucherStatusParams);
            boolean l_blnIsAccOpenVoucherFlag = true;
            String l_strBranchCode = "624";
            String l_strAccountCode = "123456";
            ArrayList l_lisRecords = new ArrayList();

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "createAccInfoRegVoucher", new Class[]{
                        List.class, boolean.class, String.class, String.class, ArrayList.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_lisRecordList, new Boolean(l_blnIsAccOpenVoucherFlag),
                l_strBranchCode, l_strAccountCode, l_lisRecords});

            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn =
                (WEB3AdminDirSecAccVoucherRecordDetail)l_lisRecords.get(0);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h = 123
            assertEquals("123", l_paramsReturn.institutionCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h = 624
            assertEquals("624", l_paramsReturn.branchCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h = 123456
            assertEquals("123456", l_paramsReturn.accountCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h = 222
            assertEquals("222", l_paramsReturn.dataCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h = 333
            assertEquals("333", l_paramsReturn.requestNumber);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� = 1
            assertEquals("1", l_paramsReturn.voucherMakeStatus);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h = 11
            assertEquals("11", l_paramsReturn.errorReasonCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = �g20070606�h
            log.debug("�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = " + l_paramsReturn.voucherSendTimestamp);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = �g20070606�h
            log.debug("�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = " + l_paramsReturn.voucherRecvTimestamp);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O = true
            assertEquals(true, l_paramsReturn.voucherFlag);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� = 1
            assertEquals(null, l_paramsReturn.infoType);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = 1"
            assertEquals("1", l_paramsReturn.voucherNumber);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�i�����j���R�[�hList�̒� == 3
     * �i�����j�����J�ݓ`�[�t���O == FALSE �̏ꍇ"
     * �@@�ŕԉ�?
     */
    public void testCreateAccInfoRegVoucher_case004()
    {
        final String STR_METHOD_NAME = "testCreateAccInfoRegVoucher_case004";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            AccOpenVoucherStatusParams l_vccOpenVoucherStatusParams = new AccOpenVoucherStatusParams();
            l_vccOpenVoucherStatusParams.setInstitutionCode("123");
            l_vccOpenVoucherStatusParams.setAccOpenRequestNumber("333");
            l_vccOpenVoucherStatusParams.setRequestCode("222");
            l_vccOpenVoucherStatusParams.setVoucherStatus("1");
            l_vccOpenVoucherStatusParams.setErrorCode("11");
            l_vccOpenVoucherStatusParams.setSerialNo("1");
            l_vccOpenVoucherStatusParams.setSendTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            l_vccOpenVoucherStatusParams.setRecvTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            List l_lisRecordList = new ArrayList();
            l_lisRecordList.add(l_vccOpenVoucherStatusParams);
            l_lisRecordList.add(l_vccOpenVoucherStatusParams);
            l_lisRecordList.add(l_vccOpenVoucherStatusParams);
            boolean l_blnIsAccOpenVoucherFlag = true;
            String l_strBranchCode = "624";
            String l_strAccountCode = "123456";
            ArrayList l_lisRecords = new ArrayList();

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "createAccInfoRegVoucher", new Class[]{
                        List.class, boolean.class, String.class, String.class, ArrayList.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_lisRecordList, new Boolean(l_blnIsAccOpenVoucherFlag),
                l_strBranchCode, l_strAccountCode, l_lisRecords});

            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn =
                (WEB3AdminDirSecAccVoucherRecordDetail)l_lisRecords.get(0);
            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn1 =
                (WEB3AdminDirSecAccVoucherRecordDetail)l_lisRecords.get(1);
            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn2 =
                (WEB3AdminDirSecAccVoucherRecordDetail)l_lisRecords.get(2);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h = 123
            assertEquals("123", l_paramsReturn.institutionCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h = 624
            assertEquals("624", l_paramsReturn.branchCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h = 123456
            assertEquals("123456", l_paramsReturn.accountCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h = 222
            assertEquals("222", l_paramsReturn.dataCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h = 333
            assertEquals("333", l_paramsReturn.requestNumber);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� = 1
            assertEquals("1", l_paramsReturn.voucherMakeStatus);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h = 11
            assertEquals("11", l_paramsReturn.errorReasonCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = �g20070606�h
            log.debug("�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = " + l_paramsReturn.voucherSendTimestamp);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = �g20070606�h
            log.debug("�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = " + l_paramsReturn.voucherRecvTimestamp);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O = true
            assertEquals(true, l_paramsReturn.voucherFlag);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� = 1
            assertEquals(null, l_paramsReturn.infoType);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = 1"
            assertEquals("1", l_paramsReturn.voucherNumber);

            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h = 123
            assertEquals("123", l_paramsReturn1.institutionCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h = 624
            assertEquals("624", l_paramsReturn1.branchCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h = 123456
            assertEquals("123456", l_paramsReturn1.accountCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h = 222
            assertEquals("222", l_paramsReturn1.dataCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h = 333
            assertEquals("333", l_paramsReturn1.requestNumber);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� = 1
            assertEquals("1", l_paramsReturn1.voucherMakeStatus);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h = 11
            assertEquals("11", l_paramsReturn1.errorReasonCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = �g20070606�h
            log.debug("�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = " + l_paramsReturn1.voucherSendTimestamp);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = �g20070606�h
            log.debug("�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = " + l_paramsReturn1.voucherRecvTimestamp);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O = true
            assertEquals(true, l_paramsReturn1.voucherFlag);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� = 1
            assertEquals(null, l_paramsReturn1.infoType);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = 1"
            assertEquals("1", l_paramsReturn1.voucherNumber);

            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�،���ЃR�[�h = 123
            assertEquals("123", l_paramsReturn2.institutionCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���X�R�[�h = 624
            assertEquals("624", l_paramsReturn2.branchCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�ڋq�R�[�h = 123456
            assertEquals("123456", l_paramsReturn2.accountCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�f�[�^�R�[�h = 222
            assertEquals("222", l_paramsReturn2.dataCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.���ʃR�[�h = 333
            assertEquals("333", l_paramsReturn2.requestNumber);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�쐬�� = 1
            assertEquals("1", l_paramsReturn2.voucherMakeStatus);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�G���[���R�R�[�h = 11
            assertEquals("11", l_paramsReturn2.errorReasonCode);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = �g20070606�h
            log.debug("�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[���M����  = " + l_paramsReturn2.voucherSendTimestamp);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = �g20070606�h
            log.debug("�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[��M����  = " + l_paramsReturn2.voucherRecvTimestamp);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�����J�ݓ`�[�t���O = true
            assertEquals(true, l_paramsReturn2.voucherFlag);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�A����� = 1
            assertEquals(null, l_paramsReturn2.infoType);
            //* �i�ԉ�?�j�ڋq���o�^�`�[���R�[�h�ڍ�.�`�[�ʔ� = 1"
            assertEquals("1", l_paramsReturn2.voucherNumber);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �i�����j�ڋq��� == null �̏ꍇ
     * �i�����j�ڋq��� == null
     * throw 80017
     */
    public void testUpdateVariousInform_case001()
    {
        final String STR_METHOD_NAME = "testUpdateVariousInform_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo = null;
            String l_strVoucherCreateStatus = null;
            String l_strErrorReasonCode = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateVariousInform", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * " �R-�P�j�e��A���e�[�u����?�� �̖߂�l���� == 0 �̏ꍇ
     *  �u���R�[�h�����݂��܂���B�v�̗�O���X���[����"   ����e��A���e�[�u��
     *  �u���R�[�h�����݂��܂���B�v�̗�O���X���[����
     */
    public void testUpdateVariousInform_case002()
    {
        final String STR_METHOD_NAME = "testUpdateVariousInform_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo =
                new WEB3AdminDirSecAccVoucherRecordDetail();
            String l_strVoucherCreateStatus = null;
            String l_strErrorReasonCode = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateVariousInform", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     *  �R-�P�j�e��A���e�[�u����?�� �̖߂�l���� == 1 �̏ꍇ�e��A���e�[�u���̍X�V
     *  1�j�i�����j�`�[�쐬�� = 1
     *  2�j�i�����j�G���[���R�R�[�h = 2
     *  3)������?���I�e��A���e�[�u�������������@@���F
     *  �e��A���e�[�u��.�،���ЃR�[�h = 123
     *  �e��A���e�[�u��.�A�����  = 1
     *  �e��A���e�[�u��.���ʃR�[�h  = 24
     *  �e��A���e�[�u��.���X�R�[�h  = 624
     *  4)�i�����j�ڋq���.�،���ЃR�[�h = 123
     *  �i�����j�ڋq���.�A����� = 1
     *  �i�����j�ڋq���.���ʃR�[�h=24
     *  �i�����j�ڋq���.���X�R�[�h  =624
     *  "   "�e��A���e�[�u���̍X�V�@@���F
     *  �e��A���e�[�u��.status = 1
     *  �e��A���e�[�u��.�F""error_reason_code = 2
     *  �e��A���e�[�u��.receipt_timestamp = ?�ݓ�?"
     */
    public void testUpdateVariousInform_case003()
    {
        final String STR_METHOD_NAME = "testUpdateVariousInform_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo =
                new WEB3AdminDirSecAccVoucherRecordDetail();
            l_accountInfo.institutionCode = "123";
            l_accountInfo.infoType = "1";
            l_accountInfo.requestNumber = "24";
            l_accountInfo.branchCode = "624";
            String l_strVoucherCreateStatus = "1";
            String l_strErrorReasonCode = "2";

            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("123");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("24");
            l_variousInforParams.setBranchCode("624");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setErrorReasonCode("4");
            TestDBUtility.insertWithDel(l_variousInforParams);
            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateVariousInform", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});

            String p_institutionCode = "123";
            String p_informDiv = "1";
            String p_requestNumber = "24";
            String p_branchCode = "624"; 
            VariousInformRow l_row = VariousInformDao.findRowByPk(
                p_institutionCode, p_informDiv, p_requestNumber, p_branchCode);
            assertEquals("1", l_row.getStatus());
            assertEquals("2", l_row.getErrorReasonCode());
            log.debug("receipt_timestamp = ?�ݓ�?" + l_row.getReceiptTimestamp());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02308,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �i�����j�ڋq��� == null �̏ꍇ
     * �i�����j�ڋq��� == null
     *   throw 80017 �ُ�
     */
    public void testUpdateAccOpenVoucherStatus_case001()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenVoucherStatus_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo = null;
            String l_strVoucherCreateStatus = null;
            String l_strErrorReasonCode = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateAccOpenVoucherStatus", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * " �R-�P�j�����J�ݍ쐬�`�[�X�e�[�^�X��?�� �̖߂�l���� == 0 �̏ꍇ
     * �u���R�[�h�����݂��܂���B�v�̗�O���X���[����"
     * ��������J�ݍ쐬�`�[�X�e�[�^�X �u���R�[�h�����݂��܂���B�v�̗�O���X���[����
     */
    public void testUpdateAccOpenVoucherStatus_case002()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenVoucherStatus_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo =
                new WEB3AdminDirSecAccVoucherRecordDetail();
            String l_strVoucherCreateStatus = null;
            String l_strErrorReasonCode = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateAccOpenVoucherStatus", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     *  �R-�P�j�����J�ݍ쐬�`�[�X�e�[�^�X��?�� �̖߂�l���� == 1 �̏ꍇ�e��A���e�[�u���̍X�V "1�j
     *  �i�����j�`�[�쐬�� = 1
     *  2�j�i�����j�G���[���R�R�[�h = 2
     *  3)������?���I�����J�ݍ쐬�`�[�X�e�[�^�X�����������@@��
     *  �����J�ݍ쐬�`�[�X�e�[�^�X.�،���ЃR�[�h = 123
     *  �����J�ݍ쐬�`�[�X�e�[�^�X.���ʃR�[�h  = 24
     *  �����J�ݍ쐬�`�[�X�e�[�^�X.�f�[�^�R�[�h  = 4
     *  �����J�ݍ쐬�`�[�X�e�[�^�X.�`�[�ʔ�  = 5
     *  4)�i�����j�ڋq���.�،���ЃR�[�h = 123
     *  �i�����j�ڋq���.�A����� = 1
     *  �i�����j�ڋq���.���ʃR�[�h=24
     *  �i�����j�ڋq���.�f�[�^�R�[�h  =4
     *  �i�����j�ڋq���.�`�[�ʔ� = 5
     *  "   "�����J�ݍ쐬�`�[�X�e�[�^�X�̍X�V�@@���F
     *  �����J�ݍ쐬�`�[�X�e�[�^�X.voucher_status = 1
     *  �����J�ݍ쐬�`�[�X�e�[�^�X.�F""error_code = 2
     *  �����J�ݍ쐬�`�[�X�e�[�^�X.recv_timestamp = ?�ݓ�?"
     */
    public void testUpdateAccOpenVoucherStatus_case003()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenVoucherStatus_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            AccOpenVoucherStatusParams l_params = this.getAccopenVoucherStatus();
            l_params.setInstitutionCode("123");
            l_params.setAccOpenRequestNumber("24");
            l_params.setRequestCode("4");
            l_params.setSerialNo("5");
            l_params.setVoucherStatus("1");
            TestDBUtility.insertWithDel(l_params);

            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo =
                new WEB3AdminDirSecAccVoucherRecordDetail();
            l_accountInfo.institutionCode = "123";
            l_accountInfo.requestNumber = "24";
            l_accountInfo.dataCode = "4";
            l_accountInfo.voucherNumber = "5";
            l_accountInfo.infoType = "1";
            String l_strVoucherCreateStatus = "1";
            String l_strErrorReasonCode = "2";

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateAccOpenVoucherStatus", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});

            String p_institutionCode = "123";
            String p_accOpenRequestNumber = "24";
            String p_requestCode = "4";
            String p_serialNo = "5";
            AccOpenVoucherStatusRow l_row = AccOpenVoucherStatusDao.findRowByPk(
                p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo );
            assertEquals("1", l_row.getVoucherStatus());
            assertEquals("2", l_row.getErrorCode());
            log.debug("recv_timestamp = ?�ݓ�?" + l_row.getRecvTimestamp());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02308,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�����j�A����� != null �̏ꍇ
     * �����j���ʃR�[�h != null �̏ꍇ
     * �i�����j�ڋq�R�[�h�̒��� == 7 �̏ꍇ
     * �i�����j�f�[�^�R�[�h != null �̏ꍇ
     * �i�����j�`�[���M���� != null �̏ꍇ" "�����j
     * �A����� == 1
     * �����j���ʃR�[�h== 12
     * �i�����j�ڋq�R�[�h== 1234567
     * �i�����j�f�[�^�R�[�h = 111
     * �i�����j�`�[���M���� = ""20070808""
     * ���󐔐��\�e��A���e�[�u��
     * �������\�e��A���e�[�u�������������@@���F
     * �،���ЃR�[�h= 123
     * �A����� = 1
     * ���ʃR�[�h  = 12
     * ���X�R�[�h = 3
     * �ڋq�R�[�h = 1234567
     * �f�[�^�R�[�h = 111
     * �`�[���M�������� = �g20070808�h
     * �ԉ�?��size == 1
     */
    public void testGetVariousInformRecord_cae001()
    {
        final String STR_METHOD_NAME = "testGetVariousInformRecord_cae001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();

            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("123");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("12");
            l_variousInforParams.setBranchCode("3");
            l_variousInforParams.setAccountCode("1234567");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setRequestCode("111");
            l_variousInforParams.setErrorReasonCode("4");
            l_variousInforParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInforParams);

            String l_strInstitutionCode = "123";
            String l_strInformDiv = "1";
            String l_strRequestNumber = "12";
            String l_strBranchCode = "3";
            String l_strAccountCode = "1234567";
            String l_strDataCode = "111";
            String l_strSendTimestamp = "20070808";

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getVariousInformRecord", new Class[]{
                    String.class, String.class, String.class,
                    String.class,String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_strInformDiv,
                l_strRequestNumber, l_strBranchCode, l_strAccountCode, l_strDataCode, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            VariousInformRow l_row = (VariousInformRow)l_lisTemp.get(0);
            assertEquals("2", l_row.getStatus());
            assertEquals("4", l_row.getErrorReasonCode());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�����j�A����� != null �̏ꍇ
     * �����j���ʃR�[�h != null �̏ꍇ
     * �i�����j�ڋq�R�[�h�̒��� < 7 �̏ꍇ
     * �i�����j�f�[�^�R�[�h != null �̏ꍇ
     * �i�����j�`�[���M���� != null �̏ꍇ" "�����j�A����� == 1
     * �����j���ʃR�[�h== 12
     * �i�����j�ڋq�R�[�h== 123456
     * �i�����j�f�[�^�R�[�h = 21
     * �i�����j�`�[���M���� = ""20070808""
     * ���󐔐��\�e��A���e�[�u��
     * �������\�e��A���e�[�u�������������@@���F
     * �،���ЃR�[�h= 123
     * �A����� = 1
     * ���ʃR�[�h  = 2
     * ���X�R�[�h = 3
     * �ڋq�R�[�h = 123456
     * �f�[�^�R�[�h = 111
     * �`�[���M�������� = �g20070808�h"  �ԉ�?��size == 1
     */
    public void testGetVariousInformRecord_cae002()
    {
        final String STR_METHOD_NAME = "testGetVariousInformRecord_cae002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();

            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("123");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("12");
            l_variousInforParams.setBranchCode("3");
            l_variousInforParams.setAccountCode("1234567");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setRequestCode("111");
            l_variousInforParams.setErrorReasonCode("4");
            l_variousInforParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInforParams);

            String l_strInstitutionCode = "123";
            String l_strInformDiv = "1";
            String l_strRequestNumber = "12";
            String l_strBranchCode = "3";
            String l_strAccountCode = "123456";
            String l_strDataCode = "111";
            String l_strSendTimestamp = "20070808";

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getVariousInformRecord", new Class[]{
                    String.class, String.class, String.class,
                    String.class,String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_strInformDiv,
                l_strRequestNumber, l_strBranchCode, l_strAccountCode, l_strDataCode, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            VariousInformRow l_row = (VariousInformRow)l_lisTemp.get(0);
            assertEquals("2", l_row.getStatus());
            assertEquals("4", l_row.getErrorReasonCode());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02308,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�����j�A����� == null �̏ꍇ
     * �����j���ʃR�[�h == null �̏ꍇ
     * �i�����j�ڋq�R�[�h�̒��� < 7 �̏ꍇ
     * �i�����j�f�[�^�R�[�h == null �̏ꍇ
     * �i�����j�`�[���M���� == null �̏ꍇ"
     * "�����j�A����� == null
     * �����j���ʃR�[�h== null
     * �i�����j�ڋq�R�[�h== 123456
     * �i�����j�f�[�^�R�[�h = null
     * �i�����j�`�[���M���� = null
     * ���󐔐��\�e��A���e�[�u��
     * �������\�e��A���e�[�u�������������@@���F
     * �،���ЃR�[�h= 123
     * �A����� = 1
     * ���ʃR�[�h  = 2
     * ���X�R�[�h = 3
     * �ڋq�R�[�h = 123456
     * �f�[�^�R�[�h = 111
     * �`�[���M�������� = �g20070808�h"
     * �ԉ�?��size == 1
     */
    public void testGetVariousInformRecord_cae003()
    {
        final String STR_METHOD_NAME = "testGetVariousInformRecord_cae003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();

            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("123");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("12");
            l_variousInforParams.setBranchCode("3");
            l_variousInforParams.setAccountCode("1234567");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setRequestCode("111");
            l_variousInforParams.setErrorReasonCode("4");
            l_variousInforParams.setSendTimestamp(WEB3DateUtility.getDate("20070808", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInforParams);

            String l_strInstitutionCode = "123";
            String l_strInformDiv = null;
            String l_strRequestNumber = null;
            String l_strBranchCode = "3";
            String l_strAccountCode = "123456";
            String l_strDataCode = null;
            String l_strSendTimestamp = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getVariousInformRecord", new Class[]{
                    String.class, String.class, String.class,
                    String.class,String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_strInformDiv,
                l_strRequestNumber, l_strBranchCode, l_strAccountCode, l_strDataCode, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            VariousInformRow l_row = (VariousInformRow)l_lisTemp.get(0);
            assertEquals("2", l_row.getStatus());
            assertEquals("4", l_row.getErrorReasonCode());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02308,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�����j�A����� == null
     * �����j���ʃR�[�h == null
     * �i�����j�ڋq�R�[�h�̒��� < 7
     * �i�����j�f�[�^�R�[�h == null
     * �i�����j�`�[���M���� == null �̏ꍇ
     * �߂�l�̒���==0�̏ꍇ��null��ԋp����
     * "   "�����j�A����� == null
     * �����j���ʃR�[�h== null
     * �i�����j�ڋq�R�[�h== 123456
     * �i�����j�f�[�^�R�[�h = null
     * �i�����j�`�[���M���� = null
     * ���󐔐��\�e��A���e�[�u��"
     *  "null��ԋp����B
     */
    public void testGetVariousInformRecord_cae004()
    {
        final String STR_METHOD_NAME = "testGetVariousInformRecord_cae004";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();

            String l_strInstitutionCode = "123";
            String l_strInformDiv = null;
            String l_strRequestNumber = null;
            String l_strBranchCode = "3";
            String l_strAccountCode = "123456";
            String l_strDataCode = null;
            String l_strSendTimestamp = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getVariousInformRecord", new Class[]{
                    String.class, String.class, String.class,
                    String.class,String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_strInformDiv,
                l_strRequestNumber, l_strBranchCode, l_strAccountCode, l_strDataCode, l_strSendTimestamp});


            List l_lisTemp = (List)l_obj;

            assertEquals(null, l_lisTemp);
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02308,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ���ʃR�[�h == null�̏ꍇ
     * ���ʃR�[�h == null
     * throw 80017
     */
    public void testGetAccOpenVoucherStatusRecord_case001()
    {
        final String STR_METHOD_NAME = "testGetAccOpenVoucherStatusRecord_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strInstitutionCode = "123";
            Object[] l_requestNumbers = null;
            String l_strDataCode = null;
            String l_strSerialNo = null;
            String l_strSendTimestamp = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getAccOpenVoucherStatusRecord", new Class[]{
                    String.class, Object[].class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_requestNumbers,
                l_strDataCode, l_strSerialNo, l_strSendTimestamp});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "���ʃR�[�h[]�̒��� == 1 �̏ꍇ
     * �i�����j�f�[�^�R�[�h != null �̏ꍇ
     * �i�����j�`�[�ʔ� != null �̏ꍇ
     * �i�����j�`�[���M���� != null �̏ꍇ
     * �i�����j�،���ЃR�[�h = 123
     * �i�����j���ʃR�[�h[0] = 1
     *   �i�����j�f�[�^�R�[�h =2
     *   �i�����j�`�[�ʔ� =3
     *    �i�����j�`�[���M���� =""20060606""
     *    ��������J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��
     *    �����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u���������@@���F
     *    �����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.�،���ЃR�[�h = 123
     *    �����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.���ʃR�[�h[0] = 1
     *   ���\���J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.�f�[�^�R�[�h =2
     *   ���\���J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.�`�[�ʔ� =3
     *   ���\���J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.�`�[���M���� =""20060606""
     *   ���\���J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.�`�[���M���� =""20060606"" "  �ԉ�?��size == 1
     */
    public void testGetAccOpenVoucherStatusRecord_case002()
    {
        final String STR_METHOD_NAME = "testGetAccOpenVoucherStatusRecord_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strInstitutionCode = "123";
            Object[] l_requestNumbers = {"1"};
            String l_strDataCode = "2";
            String l_strSerialNo = "3";
            String l_strSendTimestamp = "20060606";

            AccOpenVoucherStatusParams l_params = this.getAccopenVoucherStatus();
            l_params.setInstitutionCode("123");
            l_params.setAccOpenRequestNumber("1");
            l_params.setRequestCode("2");
            l_params.setSerialNo("3");
            l_params.setVoucherStatus("1");
            l_params.setSendTimestamp(WEB3DateUtility.getDate("20060606","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params);

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getAccOpenVoucherStatusRecord", new Class[]{
                    String.class, Object[].class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_requestNumbers,
                l_strDataCode, l_strSerialNo, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            AccOpenVoucherStatusRow l_row = (AccOpenVoucherStatusRow)l_lisTemp.get(0);
            assertEquals("1", l_row.getAccOpenRequestNumber());
            assertEquals("3", l_row.getSerialNo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "���ʃR�[�h[]�̒��� == 3 �̏ꍇ
     * �i�����j�f�[�^�R�[�h == null �̏ꍇ
     * �i�����j�`�[�ʔ� == null �̏ꍇ
     * �i�����j�`�[���M���� == null �̏ꍇ
     * �i�����j�،���ЃR�[�h = 123
     * �i�����j���ʃR�[�h[0] = 1
     * �i�����j���ʃR�[�h[1] = 2
     * �i�����j���ʃR�[�h[2] =3
     * �i�����j�f�[�^�R�[�h =null
     * �i�����j�`�[�ʔ� =null
     * �i�����j�`�[���M���� =null
     * ��������J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��
     * ���\�����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u���������@@���F
     * ���\�����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.�،���ЃR�[�h = 123
     * ���\���J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.���ʃR�[�h = 1
     *  ���\���J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.�f�[�^�R�[�h =2
     *   ���\���J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.�`�[�ʔ� =3
     *   ���\���J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��.�`�[���M���� =""20060606""
     *   �ԉ�?��size == 1
     */
    public void testGetAccOpenVoucherStatusRecord_case003()
    {
        final String STR_METHOD_NAME = "testGetAccOpenVoucherStatusRecord_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strInstitutionCode = "123";
            Object[] l_requestNumbers = {"1","2","3"};
            String l_strDataCode = null;
            String l_strSerialNo = null;
            String l_strSendTimestamp = null;

            AccOpenVoucherStatusParams l_params = this.getAccopenVoucherStatus();
            l_params.setInstitutionCode("123");
            l_params.setAccOpenRequestNumber("1");
            l_params.setRequestCode("2");
            l_params.setSerialNo("3");
            l_params.setVoucherStatus("1");
            l_params.setSendTimestamp(WEB3DateUtility.getDate("20060606","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params);

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getAccOpenVoucherStatusRecord", new Class[]{
                    String.class, Object[].class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_requestNumbers,
                l_strDataCode, l_strSerialNo, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            AccOpenVoucherStatusRow l_row = (AccOpenVoucherStatusRow)l_lisTemp.get(0);
            assertEquals("1", l_row.getAccOpenRequestNumber());
            assertEquals("3", l_row.getSerialNo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "���ʃR�[�h[]�̒��� == 3 �̏ꍇ
     * �i�����j�f�[�^�R�[�h == null �̏ꍇ
     * �i�����j�`�[�ʔ� == null �̏ꍇ
     * �i�����j�`�[���M���� == null �̏ꍇ
     * �i�����j�،���ЃR�[�h = 123�i
     * �����j���ʃR�[�h[0] = 1
     * �i�����j���ʃR�[�h[1] = 2
     * �����j���ʃR�[�h[2] =3
     * �i�����j�f�[�^�R�[�h =null
     * �i�����j�`�[�ʔ� =null
     * �i�����j�`�[���M���� =null
     * ��������J�ݓ`�[�쐬�X�e�[�^�X�e�[�u��"
     * "null��ԋp����B
     */
    public void testGetAccOpenVoucherStatusRecord_case004()
    {
        final String STR_METHOD_NAME = "testGetAccOpenVoucherStatusRecord_case004";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strInstitutionCode = "123";
            Object[] l_requestNumbers = {"1","2","3"};
            String l_strDataCode = null;
            String l_strSerialNo = null;
            String l_strSendTimestamp = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getAccOpenVoucherStatusRecord", new Class[]{
                    String.class, Object[].class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_requestNumbers,
                l_strDataCode, l_strSerialNo, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            assertEquals(null, l_lisTemp);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public static ExpAccountOpenParams getExpAccountOpenParams()
    {
        ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams();

        //1 �،���ЃR�[�h  institution_codeVARCHAR23Notnull
        l_expAccountOpenParams.setInstitutionCode("123");
        //2 �،����ID  institution_idNUMBER18Notnull
        l_expAccountOpenParams.setInstitutionId(123);
        //3 ���X�h�c  branch_idNUMBER18Notnull
        l_expAccountOpenParams.setBranchId(1232);
        //4 ���X�R�[�h  branch_codeVARCHAR23Notnull
        l_expAccountOpenParams.setBranchCode("624");
        //5 ���ʃR�[�h  acc_open_request_numberVARCHAR213Notnull
        l_expAccountOpenParams.setAccOpenRequestNumber("624");
        //8 ���������t���Oex_account_flagVARCHAR21Notnull
        l_expAccountOpenParams.setExAccountFlag(BooleanEnum.FALSE);
        //11 �����敪account_div VARCHAR21Notnull
        l_expAccountOpenParams.setAccountDiv("1");
        //12 ���͋敪order_div VARCHAR21Notnull
        l_expAccountOpenParams.setOrderDiv("1");
        //16 �ڋq���i�����jfamily_name VARCHAR240Notnull
        l_expAccountOpenParams.setFamilyName("1");
        //17 �ڋq���i�����jgiven_name VARCHAR240Notnull
        l_expAccountOpenParams.setGivenName("1");
        //18 �ڋq���i�J�i�jfamily_name_alt1 VARCHAR240Notnull
        l_expAccountOpenParams.setFamilyNameAlt1("1");
        //19 �ڋq���i�J�i�jgiven_name_alt1 VARCHAR240Notnull
        l_expAccountOpenParams.setGivenNameAlt1("1");
        //20 ����sex VARCHAR21Notnull
        l_expAccountOpenParams.setSex("1");

        //25 �X�֔ԍ�zip_codeVARCHAR27Notnull
        l_expAccountOpenParams.setZipCode("1");
        //26 �Z���Paddress_line1VARCHAR234Notnull
        l_expAccountOpenParams.setAddressLine1("1");
        //29 �Z���P�i�J�i�jaddress_line1_kana VARCHAR2 60 Notnull
        l_expAccountOpenParams.setAddressLine1Kana("1");
        //77 �����o���L�����������t���Oexperience_flag_equityNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
        //78 �M�p����t���Oexperience_flag_marginNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
        //79 ���t���Oexperience_flag_bondNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
        //80 �]���Ѝt���Oexperience_flag_wtNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
        //80 �����M���i�����j�t���Oexperience_flag_fund_skNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
        //81 �����M���i���Ѝj�t���Oexperience_flag_fund_bdNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
        //81 �敨�E�I�v�V�����t���Oexperience_flag_foNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
        //82 �O���،��t���Oexperience_flag_f_equityNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
        //83 ���̑��t���Oexperience_flag_etcNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
        //84 �o���N���i���jexperience_fromVARCHAR22NULL
        l_expAccountOpenParams.setExperienceFrom("1");
        //85 �o���N���i���jexperience_toVARCHAR22NULL
        l_expAccountOpenParams.setExperienceTo("1");
        //86 �����̂��邨������������t���Ointerest_flag_equityNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagEquity(BooleanEnum.FALSE);
        //87 �����~�j�����t���Ointerest_flag_ministockNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagMinistock(BooleanEnum.FALSE);
        //88 �M�p����t���Ointerest_flag_marginNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagMargin(BooleanEnum.FALSE);
        //89 ���t���Ointerest_flag_bondNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagBond(BooleanEnum.FALSE);
        //90 �����M���t���Ointerest_flag_fundNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagFund(BooleanEnum.FALSE);
        //91 �敨�E�I�v�V�����t���Ointerest_flag_foNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagFEquity(BooleanEnum.FALSE);
        //92 �O���،��t���Ointerest_flag_f_equityNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagFEquity(BooleanEnum.FALSE);
        //93 ���̑��t���Ointerest_flag_etcNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagEtc(BooleanEnum.FALSE);
        //105 �����p�敪id_confirm_flagNUMBER1Notnull
        l_expAccountOpenParams.setIdConfirmFlag(BooleanEnum.FALSE);
        //110 �����ғo�^�t���Oinsider_flagNUMBER1Notnull

        l_expAccountOpenParams.setInsiderFlag(BooleanEnum.FALSE);
        //147 �쐬����created_timestampDATENotnull
        l_expAccountOpenParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        //148 �X�V����last_updated_timestampDATENotnull
        l_expAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));

        l_expAccountOpenParams.setExperienceDivEquity("1");
        l_expAccountOpenParams.setExperienceDivMargin("1");
        l_expAccountOpenParams.setExperienceDivBond("1");
        l_expAccountOpenParams.setExperienceDivWt("1");
        l_expAccountOpenParams.setExperienceDivFundSk("1");
        l_expAccountOpenParams.setExperienceDivFundBd("1");
        l_expAccountOpenParams.setExperienceDivFo("1");
        l_expAccountOpenParams.setExperienceDivFEquity("1");
        l_expAccountOpenParams.setExperienceDivEtc("1");

        return l_expAccountOpenParams;
    }

    public static VariousInformParams getVariousInformRow()
    {
        VariousInformParams variousInformParams = new VariousInformParams();
        variousInformParams.setBranchCode("000");
        variousInformParams.setInstitutionCode("123");
        variousInformParams.setInformDiv("123");
        variousInformParams.setRequestNumber("123");
        variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        return variousInformParams;
    }

    public static AccOpenVoucherStatusParams getAccopenVoucherStatus()
    {
        AccOpenVoucherStatusParams l_params = new AccOpenVoucherStatusParams();
        //1 �،���ЃR�[�hinstitution_codeVARCHAR23NotNull�i�����l�j
        l_params.setInstitutionCode("123");
        //2 ���ʃR�[�hacc_open_request_numberVARCHAR213Notnull�i�����l�j
        l_params.setAccOpenRequestNumber("123");
        //3 �f�[�^�R�[�hrequest_codeVARCHAR25NotNull�i�����l�j
        l_params.setRequestCode("123");
        //4 �`�[�ʔ�serial_noVARCHAR23NotNull�i�����l�j
        l_params.setSerialNo("1");
        //5 �`�[�쐬�X�e�[�^�Xvoucher_statusVARCHAR21NotNull���N�G�X�g�f�[�^.�X�V_�`�[�쐬��
        l_params.setVoucherStatus("1");
        //6 ���M����send_timestampDATENULL�i�����l�j
        l_params.setSendTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        //7 ��M����recv_timestampDATENULL���ݓ���
        l_params.setRecvTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        //8 �G���[�R�[�herror_codeVARCHAR24NULL���N�G�X�g�f�[�^.�X�V_�G���[���R�R�[�h
        l_params.setErrorCode("1");
        //9 �X�V�҃R�[�hlast_updaterVARCHAR220NULL�i�����l�j
        l_params.setLastUpdater("1");
        //10 �쐬����created_timestampDATENotNull�i�����l�j
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        //11 �X�V����last_updated_timestampDATENotNull�i�����l�j
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        return l_params;

    }

    private void ClearData() throws WEB3BaseException
    {
        TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
        TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
        TestDBUtility.deleteAll(VariousInformRow.TYPE);
    }
}
@
