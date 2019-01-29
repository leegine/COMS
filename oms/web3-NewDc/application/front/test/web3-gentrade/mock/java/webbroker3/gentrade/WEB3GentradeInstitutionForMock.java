head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeInstitutionForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

public class WEB3GentradeInstitutionForMock extends WEB3GentradeInstitution
{

    public WEB3GentradeInstitutionForMock(InstitutionRow l_institutionRow) throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_institutionRow);
    }

}
@
