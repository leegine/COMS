head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.41.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	ServerAccessorImplForMockException.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ServerAccessorImplForMockException.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/05/08 ���� (���u) �V�K�쐬
 */
package com.fitechlabs.xtrade.kernel.comm.client;

public class ServerAccessorImplForMockException extends RuntimeException
{
    private Object obj = null;
    
    public ServerAccessorImplForMockException()
    {
        super();
    }
    
    public ServerAccessorImplForMockException(Object obj)
    {
        this.obj = obj;
    }
    
    public Object getObject()
    {
        return this.obj;
    }
}
@
