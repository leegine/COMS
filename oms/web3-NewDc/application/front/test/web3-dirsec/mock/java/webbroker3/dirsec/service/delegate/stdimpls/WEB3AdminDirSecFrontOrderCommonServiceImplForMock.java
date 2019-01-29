head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecFrontOrderCommonServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3AdminDirSecFrontOrderCommonServiceImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/03/07 ���� (���u) �V�K�쐬
 */
package webbroker3.dirsec.service.delegate.stdimpls;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ǘ��҃t�����g�����Ǘ����ʃT�[�r�XImplForMock
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecFrontOrderCommonServiceImplForMock extends WEB3AdminDirSecFrontOrderCommonServiceImpl
{
	/**
	 * ���O�o�̓��[�e�B���e�B�B<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility
			.getInstance(WEB3AdminDirSecFrontOrderCommonServiceImplForMock.class);

	public void getGrayOrder(String l_institutionCode, String l_marketCode, String l_frontSystemCode,
			WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException
	{
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
				"getGrayOrder", 
				new Class[] { String.class, String.class, String.class,
						WEB3AdminFrontProcessNumberInfoUnit.class }, 
				new Object[] { l_institutionCode, l_marketCode,
						l_frontSystemCode, l_processInfoUnit });

		if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
				"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
				"getGrayOrder", new Class[] { String.class, String.class, String.class,
						WEB3AdminFrontProcessNumberInfoUnit.class }))
		{
			log.debug("webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImplForMock.getGrayOrder(String,String,String,WEB3AdminFrontProcessNumberInfoUnit)");
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
							"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
							"getGrayOrder",
							new Class[] { String.class, String.class, String.class,
									WEB3AdminFrontProcessNumberInfoUnit.class }).asVoid();
			return;
		}
		super.getGrayOrder(l_institutionCode, l_marketCode, l_frontSystemCode, l_processInfoUnit);
	}

	public void getIfoGrayOrder(String l_institutionCode, String l_marketCode, String l_frontSystemCode,
			WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException
	{
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
				"getIfoGrayOrder",
				new Class[] { String.class, String.class, String.class,WEB3AdminFrontProcessNumberInfoUnit.class},
				new Object[] { l_institutionCode, l_marketCode,
						l_frontSystemCode, l_processInfoUnit });
		if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
				"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
				"getIfoGrayOrder", new Class[] { String.class, String.class, String.class,
						WEB3AdminFrontProcessNumberInfoUnit.class }))
		{
			log.debug("webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImplForMock.getIfoGrayOrder(String,String,String,WEB3AdminFrontProcessNumberInfoUnit)");
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
							"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
							"getIfoGrayOrder",
							new Class[] { String.class, String.class, String.class,
									WEB3AdminFrontProcessNumberInfoUnit.class }).asVoid();
			return;
		}
		super.getIfoGrayOrder(l_institutionCode,l_marketCode,l_frontSystemCode,l_processInfoUnit);
	}
	
    public String getNowOrderRoute(String l_institutionCode, String l_marketCode, String l_frontSystemCode, String l_ProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException 
    {
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
				"getNowOrderRoute",
				new Class[] { String.class, String.class, String.class,String.class,WEB3AdminFrontProcessNumberInfoUnit.class},
				new Object[] {l_institutionCode,l_marketCode,l_frontSystemCode,l_ProductType,l_processInfoUnit});
    	
    	if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
    			"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
    			"getNowOrderRoute",
    			new Class[] { String.class, String.class, String.class,String.class,WEB3AdminFrontProcessNumberInfoUnit.class}))
    	{
			log.debug("webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImplForMock.getNowOrderRoute(String, String, String,String,WEB3AdminFrontProcessNumberInfoUnit)");
			
			return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
	    			"getNowOrderRoute",
	    			new Class[] { String.class, String.class, String.class,String.class,WEB3AdminFrontProcessNumberInfoUnit.class}).asObject();
    	}
    	return super.getNowOrderRoute(l_institutionCode,l_marketCode,l_frontSystemCode,l_ProductType,l_processInfoUnit);
    }
    
    public void getVitualServerCount(String l_institutionCode, String l_marketCode, String l_frontSystemCode,String l_ProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException 
    {
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
				"getVitualServerCount",
				new Class[] {String.class,String.class,String.class,String.class,WEB3AdminFrontProcessNumberInfoUnit.class},
				new Object[] {l_institutionCode,l_marketCode,l_frontSystemCode,l_ProductType,l_processInfoUnit});
    	
    	if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
    			"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
				"getVitualServerCount",
				new Class[] {String.class,String.class,String.class,String.class,WEB3AdminFrontProcessNumberInfoUnit.class}))
    	{
			log.debug("webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImplForMock.getVitualServerCount(String, String, String,String,WEB3AdminFrontProcessNumberInfoUnit)");
			
			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl",
					"getVitualServerCount",
					new Class[] {String.class,String.class,String.class,String.class,WEB3AdminFrontProcessNumberInfoUnit.class}).asVoid();
			return;
    	}
    	super.getVitualServerCount(l_institutionCode,l_marketCode,l_frontSystemCode,l_ProductType,l_processInfoUnit);
    }
}
@
