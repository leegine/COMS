head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.28.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d956928;
filename	WEB3HttpRequestProcessor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : HttpServletRequest����������N���X(WEB3HttpRequestProcessor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/09/29 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitechlabs.xtrade.kernel.util.log.Logit;

/**
 * HttpServletRequest����������N���X<BR>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3HttpRequestProcessor
{

    /**
     * ���O���[�e�B���e�B
     */
    private static final Logit log =
        Logit.getInstance(WEB3HttpRequestProcessor.class);

    /**
     * WEB3HttpServiceMappings
     */
    private WEB3HttpServiceMappings serviceMappings;

    /**
     * �R���X�g���N�^
     */
    public WEB3HttpRequestProcessor(WEB3HttpServiceMappings l_serviceMappings)
    {
        serviceMappings = l_serviceMappings;
    }

    /**
     * �w�肳�ꂽHttpRequest����������WEB3HttpService�ɏ������f�B�X�p�b�`����B
     * 
     * @@param request
     * @@param response
     * @@throws IOException
     * @@throws ServletException
     */
    public void process(
        HttpServletRequest l_req,
        HttpServletResponse l_res)
        throws IOException, ServletException
    {

        String l_serviceName = getServiceName(l_req);
        WEB3HttpService l_service = getService(l_serviceName);
        if (l_service == null)
        {
            return;
        }
        l_service.execute(l_req, l_res);
    }

    /**
     * �w�肳�ꂽ�T�[�r�X���Ƀ}�b�s���O���ꂽ�T�[�r�X���擾����B
     * �T�[�r�X����<code>null</code>���w�肳�ꂽ�ꍇ��A
     * �T�[�r�X���o�^����Ă��Ȃ��ꍇ�́A�f�t�H���g�Ŏg�p����T�[�r�X��Ԃ��B
     * 
     * @@param l_serviceName �T�[�r�X��
     * @@return WEB3HttpService
     */
    protected WEB3HttpService getService(String l_serviceName)
    {
        WEB3HttpService l_service = null;
        if (l_serviceName != null)
        {
            l_service = getMappings().getService(l_serviceName);
            if (l_service == null)
            {
                log.debug("Required Service not found, use default service.");
                l_service = getMappings().getDefaultService();
            }
        } else {
            log.debug("ServiceName is null, use default service.");
            l_service = getMappings().getDefaultService();
        }
        return l_service;
    }

    /**
     * HttpSerlvetRequest����g�p����T�[�r�X�����擾����B<br>
     * �T�[�r�X�����ݒ肳��Ă��Ȃ��ꍇ�́A<code>null</code>��Ԃ��B
     * 
     * @@param l_req HttpServletRequest
     * @@return �T�[�r�X��
     */
    protected String getServiceName(HttpServletRequest l_req)
    {
        String l_strServiceName = null;
        String l_strPath = l_req.getPathInfo();
        if (l_strPath != null && l_strPath.length() > 1)
        {
            if (l_strPath.startsWith("/"))
            {
                l_strServiceName = l_strPath.substring(1);
            }
        }
        return l_strServiceName;
    }

    /**
     * WEB3HttpServiceMappings���擾����B
     * 
     * @@return WEB3HttpServiceMappings
     */
    protected WEB3HttpServiceMappings getMappings()
    {
        return serviceMappings;
    }

}
@
