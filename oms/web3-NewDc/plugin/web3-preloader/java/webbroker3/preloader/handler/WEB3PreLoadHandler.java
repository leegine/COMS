head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.23.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9134a0474d;
filename	WEB3PreLoadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3PreLoaderHandler�N���X(WEB3PreLoadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/07 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.preloader.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import webbroker3.preloader.WEB3PreLoaderManager;
import webbroker3.preloader.message.WEB3PreLoadRequest;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.message.Response;


/**
 * ���b�Z�[�W�ɂ��v�����[�h�����s���邽�߂̃n���h���[�B
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoadHandler implements MessageHandler
{
    
    private static final Response RESPONSE = new Response();
    
    /**
     * �v�����[�h���b�Z�[�W����������B
     * 
     * @@param l_request �v�����[�h���b�Z�[�W
     * @@return ���X�|���X���b�Z�[�W
     */
    public Response handle(WEB3PreLoadRequest l_request)
    {
//        String l_strTableName = l_request.tableName;
//        if (l_strTableName != null)
//        {
//            WEB3PreLoaderManager l_preLoaderManager =
//                (WEB3PreLoaderManager) Services.getService(WEB3PreLoaderManager.class);
//            l_preLoaderManager.load(l_strTableName);
//        }
        
        // ��M�������b�Z�[�W���A�e�[�u������List���쐬
        List l_tableNames = new ArrayList();
        
        if (l_request.tableName != null)
        {
            l_tableNames.add(l_request.tableName);
        }
        
        if (l_request.tableNames != null && l_request.tableNames.length > 0)
        {
            for (int i = 0; i < l_request.tableNames.length; i++)
            {
                l_tableNames.add(l_request.tableNames[i]);
            }
        }
        
        int l_intThreadsSize = 
            (l_request.threadsSize > 0) ? l_request.threadsSize : 1;
        
        if (!l_tableNames.isEmpty())
        {
            WEB3PreLoaderManager l_preLoaderManager = 
                (WEB3PreLoaderManager) Services.getService(
                    WEB3PreLoaderManager.class);
            l_preLoaderManager.load(
                (String[]) l_tableNames.toArray(new String[l_tableNames.size()]), 
                l_intThreadsSize);
        }
        
        return RESPONSE;
    }

}
@
