head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.37.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MockObjectParamsValue.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mock.util;

import java.util.List;

public class WEB3MockObjectParamsValue
{
    private List paramsValueList;
    public WEB3MockObjectParamsValue(List l_list)
    {
        super();
        paramsValueList = l_list;
    }
    public Object[] getFirstCalled()
    {
        return (Object[])paramsValueList.get(0);
    }
    
    public Object[] getCalled(int i)
    {
        return (Object[])paramsValueList.get(i);
    }
}
@
