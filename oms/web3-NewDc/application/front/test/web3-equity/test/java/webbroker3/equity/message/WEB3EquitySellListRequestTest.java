head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquitySellListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquitySellListRequestTest extends TestBaseForMock {

	private static WEB3LogUtility log = WEB3LogUtility
	.getInstance(WEB3EquitySellListRequestTest.class);
	
	private WEB3EquitySellListRequest equitySellListRequest = null;
	
	public WEB3EquitySellListRequestTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
		equitySellListRequest = new WEB3EquitySellListRequest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//�jthis.�\�[�g�L�[��null�ł������ꍇ
	public void testValidate_C0001()
	{
		final String STR_METHOD_NAME = "testValidate_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= null;
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�\�[�g�L�[.�v�f�����O�������ꍇ
	public void testValidate_C0002()
	{
		final String STR_METHOD_NAME = "testValidate_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{};
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	// �\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B<BR>
	// �ȉ��̍��ږ��ȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
	// �E�����R�[�h<BR>
	// �E�s��R�[�h<BR>
	// �E��t�J�n����<BR>
	// �E��t�I������
	//���ҝɏo���ӊO
	public void testValidate_C0003()
	{
		final String STR_METHOD_NAME = "testValidate_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equitySellListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.CLOSEDATE;
			equitySellListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	// �\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B<BR>
	// �ȉ��̍��ږ��ȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
	// �E�����R�[�h<BR>
	// �E�s��R�[�h<BR>
	// �E��t�J�n����<BR>
	// �E��t�I������
	//��O�ҝɏo���ӊO
	public void testValidate_C0004()
	{
		final String STR_METHOD_NAME = "testValidate_C0004()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equitySellListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.CLOSEDATE;
			equitySellListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�v���y�[�W�ԍ���null�̏ꍇ
	public void testValidate_C0005()
	{
		final String STR_METHOD_NAME = "testValidate_C0005()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equitySellListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.pageIndex = null;
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�v���y�[�W�ԍ����O�ȉ��̏ꍇ
	public void testValidate_C0006()
	{
		final String STR_METHOD_NAME = "testValidate_C0006()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equitySellListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.pageIndex = "-1111";
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�v���y�[�W�ԍ��������ȊO�̏ꍇ
	public void testValidate_C0007()
	{
		final String STR_METHOD_NAME = "testValidate_C0007()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equitySellListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.pageIndex = "aaaa";
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�y�[�W���\���s����null�̏ꍇ
	public void testValidate_C0008()
	{
		final String STR_METHOD_NAME = "testValidate_C0008()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equitySellListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.pageIndex = "1111";
			equitySellListRequest.pageSize = null;
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�y�[�W���\���s�����O�ȉ��̏ꍇ
	public void testValidate_C0009()
	{
		final String STR_METHOD_NAME = "testValidate_C0009()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equitySellListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.pageIndex = "1111";
			equitySellListRequest.pageSize = "-111";
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//�y�[�W���\���s���������ȊO�̏ꍇ
	public void testValidate_C0010()
	{
		final String STR_METHOD_NAME = "testValidate_C00010()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equitySellListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.pageIndex = "1111";
			equitySellListRequest.pageSize = "aaaa";
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//this.�s��R�[�h��null�A
    // �@@�����L�̒l�ȊO�̏ꍇ�A
    // �@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
    // �@@�@@�@@�E�P�F����
    // �@@�@@�@@�E�Q�F���
    // �@@�@@�@@�E�R�F���É�
    // �@@�@@�@@�E�U�F����
    // �@@�@@�@@�E�W�F�D�y
    // �@@�@@�@@�E�X�FNNM
    // �@@�@@�@@�E�P�O�FJASDAQ
	public void testValidate_C0011()
	{
		final String STR_METHOD_NAME = "testValidate_C00011()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equitySellListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.pageIndex = "1111";
			equitySellListRequest.pageSize = "1111";
			equitySellListRequest.marketCode = "aa"; 
			equitySellListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//correctcase
	public void testValidate_C0012()
	{
		final String STR_METHOD_NAME = "testValidate_C00012()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equitySellListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equitySellListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equitySellListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equitySellListRequest.pageIndex = "1111";
			equitySellListRequest.pageSize = "1111";
			equitySellListRequest.marketCode = "1"; 
			equitySellListRequest.validate();
		} 
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

}
@
