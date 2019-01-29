head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformReferenceServiceImplForTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.service.delegate.stdimpls;

import webbroker3.inform.message.WEB3AdminInformCommonRequest;

public class WEB3AdminInformReferenceServiceImplForTest extends WEB3AdminInformReferenceServiceImpl
{
    protected String createGetCondString(WEB3AdminInformCommonRequest l_request)
    {
        WEB3AdminInformReferenceServiceImplTest.expectMethodParam.append(
            " createGetCondString(WEB3AdminInformCommonRequest l_request)");
        return " inform_div = ? and institution_code = ?";
    }
    
    protected Object[] createGetCondDataContainer(
        String l_strInstitutionCode,
        WEB3AdminInformCommonRequest l_request)
    {
        WEB3AdminInformReferenceServiceImplTest.expectMethodParam.append(
        " createGetCondDataContainer(String l_strInstitutionCode, WEB3AdminInformCommonRequest l_request)");

        String l_inFormDiv = "11";
        String l_institutionCode = "0D";
        Object[] l_obj = {l_inFormDiv, l_institutionCode};
        return l_obj;
    }
}
@
