head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.23.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9134a0474d;
filename	WEB3PreLoadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3PreLoaderRequest�N���X(WEB3PreLoadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/07 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.preloader.message;

import com.fitechlabs.xtrade.kernel.message.Request;


/**
 * �v�����[�h�����s���郁�b�Z�[�W�B
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoadRequest extends Request
{
    
    public static final String PTYPE = "preload";
    
    /**
     * �e�[�u����
     * 
     * @@deprecated
     */
    public String tableName;
    
    /**
     * �e�[�u�����̔z�� 
     */
    public String[] tableNames;
    
    /**
     * �X���b�h��
     */
    public int threadsSize;

}
@
