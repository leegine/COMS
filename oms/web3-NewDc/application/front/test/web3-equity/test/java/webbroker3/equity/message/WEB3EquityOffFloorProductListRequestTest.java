head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOffFloorProductListRequestTest.java;


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

public class WEB3EquityOffFloorProductListRequestTest extends TestBaseForMock {

	private static WEB3LogUtility log = WEB3LogUtility
	.getInstance(WEB3EquityOffFloorProductListRequestTest.class);

    private WEB3EquityOffFloorProductListRequest equityOffFloorProductListRequest = null;

	public WEB3EquityOffFloorProductListRequestTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
		equityOffFloorProductListRequest = new WEB3EquityOffFloorProductListRequest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//�\�[�g�L�[��null�̏ꍇ
	public void testValidate_C0001()
	{
		final String STR_METHOD_NAME = "testValidate_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equityOffFloorProductListRequest.sortKeys= null;
			equityOffFloorProductListRequest.validate();
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
			equityOffFloorProductListRequest.sortKeys= new WEB3EquitySortKey[]{};
			equityOffFloorProductListRequest.validate();
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
			equityOffFloorProductListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equityOffFloorProductListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equityOffFloorProductListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equityOffFloorProductListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01698, l_ex
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
			equityOffFloorProductListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equityOffFloorProductListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
			equityOffFloorProductListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.TRADEMARKET;
			equityOffFloorProductListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.ACCOUNTTYPE;
			equityOffFloorProductListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equityOffFloorProductListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equityOffFloorProductListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equityOffFloorProductListRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01698, l_ex
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
	public void testValidate_C0005()
	{
		final String STR_METHOD_NAME = "testValidate_C0005()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			equityOffFloorProductListRequest.sortKeys= new WEB3EquitySortKey[]{
				new WEB3EquitySortKey(),new WEB3EquitySortKey(),new WEB3EquitySortKey()};
			equityOffFloorProductListRequest.sortKeys[0].keyItem = WEB3EquityKeyItemDef.PRODUCTCODE;
			equityOffFloorProductListRequest.sortKeys[1].keyItem = WEB3EquityKeyItemDef.TRADEMARKET;
			equityOffFloorProductListRequest.sortKeys[2].keyItem = WEB3EquityKeyItemDef.TRADEMARKET;
			equityOffFloorProductListRequest.sortKeys[0].ascDesc = WEB3AscDescDef.ASC; 
			equityOffFloorProductListRequest.sortKeys[1].ascDesc = WEB3AscDescDef.ASC; 
			equityOffFloorProductListRequest.sortKeys[2].ascDesc = WEB3AscDescDef.ASC; 
			equityOffFloorProductListRequest.validate();
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
