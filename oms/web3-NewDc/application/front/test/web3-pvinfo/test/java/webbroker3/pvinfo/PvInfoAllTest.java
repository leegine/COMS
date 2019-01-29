head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	PvInfoAllTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : PvInfoAllTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/03/01 金傑 (中訊) 新規作成
 */
package webbroker3.pvinfo;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoDirectChangeServiceImplTest;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3AdminPvInfoDirectRegistServiceImplTest;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3PvInfoPrivateInformationDetailServiceImplTest;
import webbroker3.pvinfo.service.delegate.stdimpls.WEB3PvInfoPrivateInformationListServiceImplTest;

public class PvInfoAllTest
{
	private static boolean WEB3PvInfoDataManagerImplTest = true;

	private static boolean WEB3PvInfoPrivateInformationListServiceImplTest = true;

	private static boolean WEB3PvInfoPrivateInformationDetailServiceImplTest = true;

    private static boolean WEB3AdminPvInfoDirectChangeServiceImplTest = true;
	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		if (WEB3PvInfoDataManagerImplTest)
		{
			suite.addTestSuite(WEB3PvInfoDataManagerImplTest.class);
		}

		if (WEB3PvInfoPrivateInformationListServiceImplTest)
		{
			suite.addTestSuite(WEB3PvInfoPrivateInformationListServiceImplTest.class);
            suite.addTestSuite(WEB3AdminPvInfoDirectRegistServiceImplTest.class);
		}

		if (WEB3PvInfoPrivateInformationDetailServiceImplTest)
		{
			suite.addTestSuite(WEB3PvInfoPrivateInformationDetailServiceImplTest.class);
		}

        if (WEB3AdminPvInfoDirectChangeServiceImplTest)
        {
            suite.addTestSuite(WEB3AdminPvInfoDirectChangeServiceImplTest.class);
        }
        
        suite.addTestSuite(WEB3PvInfoRegistTargetAccountUploadCsvTest_xx.class);
        suite.addTestSuite(WEB3PvInfoDataManagerImpl_fenghtTest.class);

		return suite;
	}
}
@
