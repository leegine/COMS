head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	ServerAccessorImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ServerAccessorImplForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/08 金傑 (中訊) 新規作成
*/
package com.fitechlabs.xtrade.kernel.comm.client;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;

public class ServerAccessorImplForMock implements ServerAccessor
{

    private Map l_mapValue = new HashMap();
    private String l_strKey = null;
    
    public Response doRequestO(String arg0) throws CommunicationException, ServerException
    {
        return null;
    }

    public Response doRequest(Request arg0) throws CommunicationException, ServerException
    {
        if(!this.l_mapValue.isEmpty())
        {
            return (Response) this.l_mapValue.get(this.l_strKey);
        }
        else
        {
            throw new ServerAccessorImplForMockException(arg0);
        }
    }

    public String doRequest(String arg0) throws CommunicationException, ServerException
    {
        return null;
    }
    
    public void setValue(String l_strKey,Object l_objValue)
    {
        this.l_mapValue.put(l_strKey,l_objValue);
        this.l_strKey = l_strKey;
    }

}
@
