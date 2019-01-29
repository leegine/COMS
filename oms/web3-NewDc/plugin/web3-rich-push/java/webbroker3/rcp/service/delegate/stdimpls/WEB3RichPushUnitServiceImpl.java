head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�f�[�^�v�b�V���@@�\�P�ʃT�[�r�X���ʋ@@�\����(WEB3RichPushUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 ��(FLJ) �V�K�쐬
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.*;
import webbroker3.rcp.*;
import webbroker3.rcp.service.delegate.*;
import webbroker3.util.*;

/**
 * ���b�`�N���C�A���g�f�[�^�v�b�V���@@�\�P�ʃT�[�r�X���ʋ@@�\����
 * @@author ��
 * @@version 1.0
 */
public abstract class WEB3RichPushUnitServiceImpl
    implements WEB3RichPushUnitService
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushUnitServiceImpl.class);

    /**
     * ���b�`�N���C�A���g�f�[�^�v�b�V��
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     */
    public abstract void push(long l_lngFromAccountId, long l_lngToAccountId) throws
        DataQueryException, DataNetworkException;

    /**
     * ���b�`�N���C�A���g�v�b�V���I�u�W�F�N�g�\�[�g�p�R���e�N�X�g�֕ۑ�
     *
     * @@param l_lstPushObjects List
     * @@param l_mapPushHistoryObjects Map
     */
    protected void saveToContext(List l_lstPushObjects)
    {

        final String STR_METHOD_NAME = "saveToContext()";

        if (l_lstPushObjects == null)
        {
            log.error(
                "l_lstPushRecords==nulll! save fail!");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        WEB3RichPushObjectContext l_context = (WEB3RichPushObjectContext)
            ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3RichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME
            );
        //�v�b�V���I�u�W�F�N�g�ꗗ�ǉ�
        List l_lstAllObjects = l_context.getPushObjects();
        l_lstAllObjects.addAll(l_lstPushObjects);

        log.exiting(STR_METHOD_NAME);

    }

}
@
