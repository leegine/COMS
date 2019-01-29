head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LogUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�o�̓��[�e�B���e�B�N���X(WEB3LogUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �����@@���F(SRA) �V�K�쐬
                   2004/07/26 �����(���u) WEB3BaseException -> WEB3Exception
*/
package webbroker3.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import webbroker3.common.WEB3Exception;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3GenRequest;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * ���O�o�̓��[�e�B���e�B�N���X�B<BR>
 *<BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.util.log.Logit
 */
public final class WEB3LogUtility
{
    /**
     * ���O�o�͕�����F���\�b�h���B<BR>
     */
    private final static String METHOD_NAME = "���\�b�h���F";
    /**
     * ���O�o�͕�����FENTER�B<BR>
     */
    private final static String ENTER = " �F ENTER";
    /**
     * ���O�o�͕�����FEXIT�B<BR>
     */
    private final static String EXIT = " �F EXIT";
    /**
     * ���O�o�͕�����F�G���[�R�[�h�B<BR>
     */
    private final static String ERROR_CODE = "\t�G���[�R�[�h �F ";
    /**
     * ���O�o�͕�����F�^�O���B<BR>
     */
    private final static String ERROR_TAG = "\t�^�O�� �F ";
    /**
     * ���O�o�͕�����F�G���[���b�Z�[�W�B<BR>
     */
    private final static String ERROR_INFO_MESSAGE = "\t�G���[���b�Z�[�W �F ";
    /**
     * ���O�o�͕�����F�G���[�������\�b�h�B<BR>
     */
    private final static String ERROR_METHOD = "\t�G���[�������\�b�h �F ";
    /**
     * ���O�o�͕�����F�G���[���e�B<BR>
     */
    private final static String ERROR_MESSAGE = "\t�G���[���e �F ";
    /**
     * ���O�o�͕�����F�X�^�b�N�g���[�X�B<BR>
     */
    private final static String STACK_TRACE = "\t�X�^�b�N�g���[�X �F ";
    /**
     * ���O�o�͕�����F����������O�̃X�^�b�N�g���[�X�B<BR>
     */
    private final static String EXP_STACK_TRACE = "\t����������O�̃X�^�b�N�g���[�X �F ";
    /**
     * ���A���s�����B<BR>
     */
    private static String newLine = "\n";
    /**
     * �e�N���X�p�� Logit �I�u�W�F�N�g��ێ�����B<BR>
     */
    private static HashMap logs;
    /**
     * ���O�o�͂��s�� Logit �I�u�W�F�N�g�B<BR>
     */
    private Logit log;

    static
    {
        logs = new HashMap();
        try
        {
            newLine = System.getProperty("line.separator");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_clazz ���O�o�͑Ώۂ̃N���X
     */
    private WEB3LogUtility(Class l_clazz)
    {
        log = Logit.getInstance(l_clazz);
    }

    /**
     * WEB3LogUtility �N���X�̃C���X�^���X�𐶐����ĕԂ��B<BR>
     *<BR>
     * @@param l_clazz ���O�o�͑Ώۂ̃N���X
     * @@return WEB3LogUtility �N���X�̃C���X�^���X
     */
    public static WEB3LogUtility getInstance(Class l_clazz)
    {
        WEB3LogUtility l_logUtil = null;

        if ((l_logUtil = (WEB3LogUtility)logs.get(l_clazz.getName())) == null)
        {
            l_logUtil = new WEB3LogUtility(l_clazz);
            logs.put(l_clazz.getName(), l_logUtil);
        }
        return l_logUtil;
    }

    /**
     * ���\�b�h�ɓ��������Ƃ����O�ɏo�͂���B<BR>
     *<BR>
     * @@param l_sourceMethod ���O�o�͑Ώۂ̃��\�b�h��
     */
    public void entering(String l_sourceMethod)
    {
        if (log.ison())
        {
            log.debug(METHOD_NAME + l_sourceMethod + ENTER);
        }
    }

    /**
     * ���\�b�h����ޏo�������Ƃ����O�ɏo�͂���B<BR>
     *<BR>
     * @@param l_sourceMethod ���O�o�͑Ώۂ̃��\�b�h��
     */
    public void exiting(String l_sourceMethod)
    {
        if (log.ison())
        {
            log.debug(METHOD_NAME + l_sourceMethod + EXIT);
        }
    }

    /**
     * �f�o�b�O�������O�ɏo�͂���B<BR>
     *<BR>
     * @@param l_message �f�o�b�O��
     */
    public void debug(String l_message)
    {
        if (log.ison())
        {
            log.debug(l_message);
        }
    }

    /**
     * �������O�ɏo�͂���B<BR>
     *<BR>
     * @@param l_message ���
     */
    public void info(String l_message)
    {
        log.info(l_message);
    }

    /**
     * �x�������O�ɏo�͂���B<BR>
     *<BR>
     * @@param l_message �x��
     */
    public void warn(String l_message)
    {
        log.warn(l_message);
    }

    /**
     * �G���[�����O�ɏo�͂���B<BR>
     *<BR>
     * @@param l_message �G���[
     */
    public void error(String l_message)
    {
        log.error(l_message);
    }

    /**
     * �Ɩ����W�b�N�ŃG���[�����������ꍇ�AWEB�w�Ƀ��X�|���X��Ԃ��O�ɃG���[����<BR>
     * ���O�ɏo�͂���i��菈���p�j�B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     * @@param l_message �G���[���b�Z�[�W
     * @@param l_exception ��O�I�u�W�F�N�g
     */
    public void error(WEB3GenRequest l_request, String l_message, WEB3Exception l_exception)
    {
        log.error(l_message + exceptionInfo(l_exception));
    }

    /**
     * �Ɩ����W�b�N�ŃG���[�����������ꍇ�AWEB�w�Ƀ��X�|���X��Ԃ��O�ɃG���[����<BR>
     * ���O�ɏo�͂���i���菈���p�j�B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     * @@param l_message �G���[���b�Z�[�W
     * @@param l_exception ��O�I�u�W�F�N�g
     */
    public void error(WEB3BackRequest l_request, String l_message, WEB3Exception l_exception)
    {
        log.error(l_message + exceptionInfo(l_exception));
    }

    /**
     * �Ɩ����W�b�N�ŃG���[�����������ꍇ�AWEB�w�Ƀ��X�|���X��Ԃ��O�ɃG���[����<BR>
     * ���O�ɏo�͂���i��菈���p�j�B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     * @@param l_message �G���[���b�Z�[�W
     * @@param l_errorInfo �G���[���
     * @@param l_exception ��O�I�u�W�F�N�g
     */
    public void error(WEB3GenRequest l_request, String l_message, ErrorInfo l_errorInfo, Exception l_exception)
    {
        log.error(l_message + errorInfo(l_errorInfo, l_exception));
    }

    /**
     * �Ɩ����W�b�N�ŃG���[�����������ꍇ�AWEB�w�Ƀ��X�|���X��Ԃ��O�ɃG���[����<BR>
     * ���O�ɏo�͂���i���菈���p�j�B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     * @@param l_message �G���[���b�Z�[�W
     * @@param l_errorInfo �G���[���
     * @@param l_exception ��O�I�u�W�F�N�g
     */
    public void error(WEB3BackRequest l_request, String l_message, ErrorInfo l_errorInfo, Exception l_exception)
    {
        log.error(l_message + errorInfo(l_errorInfo, l_exception));
    }

    /**
     * �f�o�b�O�������O�ɏo�͂���iDebug�p�j�B<BR>
     *<BR>
     * @@param l_message �f�o�b�O���b�Z�[�W
     * @@param l_throwable ��O�I�u�W�F�N�g
     */
    public void debug(String l_message, Throwable l_throwable)
    {
        if (l_throwable instanceof WEB3Exception)
        {
            if (log.ison())
            {
                log.debug(l_message + exceptionInfo((WEB3Exception)l_throwable));
            }
        }
        else
        {
            log.debug(l_message, l_throwable);
        }
        
    }

    /**
     * �x�������O�ɏo�͂���iDebug�p�j�B<BR>
     *<BR>
     * @@param l_message �x�����b�Z�[�W
     * @@param l_throwable ��O�I�u�W�F�N�g
     */
    public void warn(String l_message, Throwable l_throwable)
    {
        log.warn(l_message, l_throwable);
    }

    /**
     * �G���[�����O�ɏo�͂���iDebug�p�j�B<BR>
     *<BR>
     * @@param l_message �G���[���b�Z�[�W
     * @@param l_throwable ��O�I�u�W�F�N�g
     */
    public void error(String l_message, Throwable l_throwable)
    {
        if (l_throwable instanceof WEB3Exception)
        {
            log.error(l_message + exceptionInfo((WEB3Exception)l_throwable));
        }
        else
        {
            log.error(l_message, l_throwable);
        }       
        
    }
    
    /**
     * Debug���[�h�̃I���^�I�t������s���B<BR>
     * log.ison()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return Debug���[�h���I���̏ꍇ��true���A�I�t�̏ꍇ��false��Ԃ��B<BR>
     */
    public boolean ison()
    {
        return log.ison();
    }

    /**
     * ��O�I�u�W�F�N�g���̕������Ԃ��B<BR>
     *<BR>
     * @@param l_exception ��O�I�u�W�F�N�g
     * @@return ��O�I�u�W�F�N�g���̕�����
     */
    private String exceptionInfo(WEB3Exception l_exception)
    {
        StringBuffer l_sbInfo = new StringBuffer("");

        if (l_exception != null)
        {
            ErrorInfo l_errorInfo = l_exception.getErrorInfo();
            l_sbInfo.append(newLine + ERROR_CODE + l_errorInfo.error_code);
            l_sbInfo.append(newLine + ERROR_TAG + l_errorInfo.error_tag);
            l_sbInfo.append(newLine + ERROR_INFO_MESSAGE + l_errorInfo.error_message);
            l_sbInfo.append(newLine + ERROR_METHOD + l_exception.getErrorMethod());
            l_sbInfo.append(newLine + ERROR_MESSAGE + l_exception.getErrorMessage());
            ByteArrayOutputStream l_baos = new ByteArrayOutputStream();
            PrintStream l_ps = new PrintStream(l_baos);
            ((Exception)l_exception).printStackTrace(l_ps);
            l_sbInfo.append(newLine + STACK_TRACE + new String(l_baos.toByteArray()));
            l_ps.close();
            try
            {
                l_baos.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            Exception exp = l_exception.getException();
            if (exp != null)
            {
                l_baos = new ByteArrayOutputStream();
                l_ps = new PrintStream(l_baos);
                exp.printStackTrace(l_ps);
                l_sbInfo.append(newLine + EXP_STACK_TRACE + new String(l_baos.toByteArray()));
                l_ps.close();
                try
                {
                    l_baos.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return l_sbInfo.toString();
    }

    /**
     * �G���[���̕������Ԃ��B<BR>
     *<BR>
     * @@param l_errorInfo �G���[���
     * @@param l_exp ��O�I�u�W�F�N�g
     * @@return �G���[���̕�����
     */
    private String errorInfo(ErrorInfo l_errorInfo, Exception l_exp)
    {
        StringBuffer l_sbInfo = new StringBuffer("");

        if (l_errorInfo != null)
        {
            l_sbInfo.append(newLine + ERROR_CODE + l_errorInfo.error_code);
            l_sbInfo.append(newLine + ERROR_TAG + l_errorInfo.error_tag);
            l_sbInfo.append(newLine + ERROR_INFO_MESSAGE + l_errorInfo.error_message);
            if (l_exp != null)
            {
                ByteArrayOutputStream l_baos = new ByteArrayOutputStream();
                PrintStream l_ps = new PrintStream(l_baos);
                l_exp.printStackTrace(l_ps);
                l_sbInfo.append(newLine + STACK_TRACE + new String(l_baos.toByteArray()));
                l_ps.close();
                try
                {
                    l_baos.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return l_sbInfo.toString();
    }
}
@
