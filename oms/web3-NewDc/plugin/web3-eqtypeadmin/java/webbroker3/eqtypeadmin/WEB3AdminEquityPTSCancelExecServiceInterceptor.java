head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSCancelExecServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����iPTS�j�o������T�[�r�X�C���^�Z�v�^(WEB3AdminEquityPTSCancelExecServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 ���n(���u) �V�K�쐬���f��178
*/

package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҁE�����iPTS�j�o������T�[�r�X�C���^�Z�v�^)<BR>
 * �Ǘ��ҁE�����iPTS�j�o������T�[�r�X�C���^�Z�v�^�N���X
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3AdminEquityPTSCancelExecServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminEquityPTSCancelExecServiceInterceptor.class);

    /**
     * @@roseuid 4795B08501F4
     */
    public WEB3AdminEquityPTSCancelExecServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * (������)<BR>
     * null��ԋp����B<BR>
     * @@param l_method - (���\�b�h)
     * @@param l_serviceParams - (�T�[�r�X����)
     * @@return Object
     * @@roseuid 4773155E0220
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        return null;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * PTS������ԊǗ�.TIMESTAMP_TAG<BR>
     * PTS������ԊǗ�.OFFSET_TAG<BR>
     * PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 4773156703D5
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        //PTS������ԊǗ�������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //PTS������ԊǗ�������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.OFFSET_TAG,
            null);

        //PTS������ԊǗ�������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * PTS������ԊǗ�.TIMESTAMP_TAG<BR>
     * PTS������ԊǗ�.OFFSET_TAG<BR>
     * PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 4773157003E4
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        //PTS������ԊǗ�������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //PTS������ԊǗ�������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.OFFSET_TAG,
            null);

        //PTS������ԊǗ�������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
